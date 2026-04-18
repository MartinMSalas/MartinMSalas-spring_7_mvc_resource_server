package com.esparta.spring7restmvcresourceserver.services;

import com.esparta.spring7restmvcresourceserver.model.BeerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/*
 * Author: M
 * Date: 04-Feb-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@Service
public class BeerCSVServiceImpl implements BeerCSVService {
    @Override
    public List<BeerCSVRecord> beerCSVRecords(File csvFile)  {

        try {
            List<BeerCSVRecord> beerCSVRecordList = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                    .withType(BeerCSVRecord.class)
                    .build().parse();
            return List.copyOf(beerCSVRecordList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<BeerCSVRecord> beerCSVRecords(String path) {
        // Path must not be null
        if (path == null) {
            throw new IllegalArgumentException("Path must not be null");
        }
        // Path must not be empty
        if (path.isEmpty()){
            throw new IllegalArgumentException("Path must not be empty");
        }

        File beerCSVFile = new File(path);

        // Validate that the file exists
        if (!beerCSVFile.exists()) {
            throw new IllegalArgumentException("File does not exist: " + path);
        }

        return beerCSVRecords(beerCSVFile);
    }
}
