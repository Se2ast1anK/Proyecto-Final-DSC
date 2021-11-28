package pe.isil;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {


        System.out.println("INICIANDO PROYECTO S3");

        //Access-Key
        //Secret-Key


        AWSCredentials credentials = new BasicAWSCredentials("","");

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        //listar los buckets
        List<Bucket> buckets = s3Client.listBuckets();
        buckets.forEach(System.out::println);
        System.out.printf("la cantidad de buckets es: "+buckets.size());

        //Crear nuevos bucket
        final String BUCKET_NAME = "pe.reti";
        if(s3Client.doesBucketExistV2(BUCKET_NAME)) {
            System.out.printf("El bucket ["+BUCKET_NAME+ "] ya existe!");
        }else{
            s3Client.createBucket(BUCKET_NAME);
            System.out.printf("El bucket ["+BUCKET_NAME+ "]  se ha creado!");
        }


                //Subir el archivo al S#

        File SourceFile = new File("aws-java-s3/src/main/resources/input/publicidad.txt");
        String s3Object = "reti/extra/publicidad.txt";

        s3Client.putObject(BUCKET_NAME, s3Object, SourceFile);
        System.out.println("Se subió a"+s3Object+" en el bucket ["+BUCKET_NAME+ "]" );


        /////////////////////////////

            //Descaargar archivoss desde el s3

        S3Object object = s3Client.getObject(BUCKET_NAME, s3Object);
        S3ObjectInputStream objectContent = object.getObjectContent();

        File SourceTarget = new File("aws-java-s3/src/main/resources/output/Publi.txt");

        try {
            java.nio.file.Files.copy(objectContent, SourceTarget.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Se descargo a"+SourceTarget.toPath()+" en el bucket ["+BUCKET_NAME+ "]" );
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Eliminar  objeto del S3

        s3Client.deleteObject(BUCKET_NAME, s3Object);
        System.out.println("Se ha eliminado el archivo '"+s3Object+"'desde el bucket["+BUCKET_NAME+ "]" );

        //Eliminar el bucket
        s3Client.deleteBucket(BUCKET_NAME);
        System.out.println("Se ha eliminado el bucket["+BUCKET_NAME+ "]" );
    }




}
