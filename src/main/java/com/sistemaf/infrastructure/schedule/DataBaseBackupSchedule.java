package com.sistemaf.infrastructure.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sistemaf.core.SistemFApiProperty;
import com.smattme.MysqlExportService;

@Component
public class DataBaseBackupSchedule {

    private static final Logger log = LoggerFactory.getLogger(DataBaseBackupSchedule.class);
    
    @Autowired
    MysqlExportService mysqlExportService;
    
	@Autowired
	private SistemFApiProperty properties;
	
    @Scheduled(cron = "0 0 23 * * *")
	public void backup() throws SQLException, ClassNotFoundException, IOException {
    	log.info("Inicio de backup");
    	mysqlExportService.export();
		File file = mysqlExportService.getGeneratedZipFile();
		this.copyFile(file);
		log.info(file.getAbsolutePath());
		mysqlExportService.clearTempFiles(false);
		
		log.info("Finalizado");
	}
	
	@SuppressWarnings("resource")
	private void copyFile(File source) throws IOException {
		File destination = new File(properties.getPastaBackup()+"/"+"backup_" + LocalDateTime.now()
			.format(new DateTimeFormatterFactory("dd-MM-YYYY HH:mm:ss").createDateTimeFormatter()) +".zip");
		destination.mkdir();
		log.info("Copia: " + destination.getAbsolutePath());
        if (destination.exists())
            destination.delete();
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen())
                sourceChannel.close();
            if (destinationChannel != null && destinationChannel.isOpen())
                destinationChannel.close();
       }
   }
}
