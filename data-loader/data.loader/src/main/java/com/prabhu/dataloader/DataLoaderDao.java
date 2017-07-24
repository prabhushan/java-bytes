package com.prabhu.dataloader;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.prabhu.dataloader.model.DoctorInfo;
import com.prabhu.dataloader.utilities.FileUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Component
public class DataLoaderDao {

	private static final String FILE_PATH = "D:\\cleanup\\data.txt";
    private final MongoTemplate mongoTemplate;

    @Autowired
    public DataLoaderDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

   public void exportData() throws IOException{
//	   List<DoctorInfo> listDoctor = this.mongoTemplate.find(new Query().limit(10000),DoctorInfo.class, "doctorinfo");
//	   listDoctor.stream().forEach(s->System.out.println(s.getDoctorDetails()));
	   DBCollection collection = mongoTemplate.getCollection("doctorinfo");
	     DBCursor cursor = collection.find();        
	     while(cursor.hasNext()){
	         DBObject obj = cursor.next();
		     FileUtil.writeToFile(obj.toString(), FILE_PATH);

	     }
   }

}