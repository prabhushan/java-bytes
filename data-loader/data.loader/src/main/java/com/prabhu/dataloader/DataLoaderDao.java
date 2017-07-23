package com.prabhu.dataloader;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.prabhu.dataloader.model.DoctorInfo;

import ch.qos.logback.core.net.SyslogOutputStream;

@Component
public class DataLoaderDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DataLoaderDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

   public void exportData(){
//	   List<DoctorInfo> listDoctor = this.mongoTemplate.find(new Query().limit(10000),DoctorInfo.class, "doctorinfo");
//	   listDoctor.stream().forEach(s->System.out.println(s.getDoctorDetails()));
	   DBCollection collection = mongoTemplate.getCollection("doctorinfo");
	     DBCursor cursor = collection.find();        
	     while(cursor.hasNext()){
	         DBObject obj = cursor.next();
		     System.out.println(obj);

	     }
   }

}