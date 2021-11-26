package pe.isil;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;

import pe.isil.model.UserReti;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

public class Main {
    public static final  String tabla = "UsuarioReti";
    public static final String Clave_Particion = "IdUsuario";

//-----------------------------------------------
    public static void main(String[] args) {
        System.out.println("Iniciando AWS DynamoDB en Reti");

        AWSCredentials credentials = new BasicAWSCredentials("AKIA46CZA5EPNLQE6OGC",
                "1+9itd8I4bTXfLujbPWFBDcL2sFHeJNARkkNm3EF");


        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();



        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);

       // createUsuarioTable(dynamoDB);

        //Agregar Usuario
        UserReti userReti = new UserReti("Jorge","Henriquez",20, Arrays.asList("ADMIN","USER"),true );
        InsertDataUserTable(dynamoDB,userReti );

        //Eliminar usuarios
        String uuid = "";
        deleteOneUser(dynamoDB, uuid, userReti);

    }
//-----------------------------------------------------------
    private static void deleteOneUser(DynamoDB dynamoDB, String uuid, UserReti userReti) {

        Table table = dynamoDB.getTable(tabla);

        //Eliminar Usuario (tipo filtro)

        DeleteItemSpec item = new DeleteItemSpec()
                .withPrimaryKey(new PrimaryKey(Clave_Particion, uuid))
                //
                .withConditionExpression("firstName = :val")
                .withValueMap(new ValueMap().withString(":val",userReti.getFirstName()))
                ;

        try {
            table.deleteItem(item);
            System.out.println("Usuario Eliminado Correctamente");
        }catch (Exception e){
            System.err.println("No se pudo eliminar el usuario con uuid: "+ uuid +"y firstName: " + userReti.getFirstName());
            System.err.println(e.getMessage());
        }
    }

    private static void InsertDataUserTable(DynamoDB dynamoDB, UserReti userReti) {

        Table table = dynamoDB.getTable(tabla);

        String uuid = UUID.randomUUID().toString();

//traemos campos UserReti
        Item item = new Item()
                .withPrimaryKey(Clave_Particion,uuid)
                    .withString("firstName", userReti.getFirstName())
                    .withString("lastName", userReti.getLastName())
                    .withNumber("age",userReti.getAge())
                    .withStringSet("roles",new HashSet<>(userReti.getRoles()))
                    .withBoolean("active", userReti.getActive());


        table.putItem(item);
        System.out.println("Nuevo Usuario Registrado: \n"+ item.toJSONPretty() );
    }

    private static void createUsuarioTable(DynamoDB dynamoDB) {
        //Clave de partición
        KeySchemaElement ks = new KeySchemaElement()
                .withAttributeName(Clave_Particion)
                .withKeyType(KeyType.HASH);

        //Inidicador para capacidad de lectura y escritura
        ProvisionedThroughput pt = new ProvisionedThroughput()
                .withReadCapacityUnits(5L)
                .withWriteCapacityUnits(5L);

        //Atributo Clave (clave de ordenacion
        AttributeDefinition ad = new AttributeDefinition()
                .withAttributeName(Clave_Particion)
                .withAttributeType(ScalarAttributeType.S);


        //Creacion de tabla
        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tabla)
                .withKeySchema(ks)
                .withProvisionedThroughput(pt)
                .withAttributeDefinitions(ad);


        //Texto para ejecucion
        Table table = dynamoDB.createTable(request);
        try {
            table.waitForActive();
            System.out.println("Se ha creado la tabla "+tabla+" Correctamente");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
