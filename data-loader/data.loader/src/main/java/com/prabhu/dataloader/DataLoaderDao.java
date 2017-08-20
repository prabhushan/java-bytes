package com.prabhu.dataloader;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.prabhu.dataloader.utilities.FileUtil;

@Component
public class DataLoaderDao {

	@Autowired
	Environment environment;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public DataLoaderDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

   public void exportData() throws IOException{
//	   List<DoctorInfo> listDoctor = this.mongoTemplate.find(new Query().limit(10000),DoctorInfo.class, "doctorinfo");
//	   listDoctor.stream().forEach(s->System.out.println(s.getDoctorDetails()));
	   String filePath=environment.getProperty("file.path");
	   File dataFile = new File(filePath);
	   DBCollection collection = mongoTemplate.getCollection("doctorinfo");
	     DBCursor cursor = collection.find();        
	     while(cursor.hasNext()){
	         DBObject obj = cursor.next();
		     FileUtil.writeToFile(dataFile,obj.toString());

	     }
   }

}