package com.ebay.ebayclientapi.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebay.ebayclientapi.constant.Constants.CountryConstant;
import com.ebay.ebayclientapi.constant.Constants.CsvConstant;
import com.ebay.ebayclientapi.entity.request.inventoryitem.Availability;
import com.ebay.ebayclientapi.entity.request.inventoryitem.AvailabilityDistribution;
import com.ebay.ebayclientapi.entity.request.inventoryitem.FulfillmentTime;
import com.ebay.ebayclientapi.entity.request.inventoryitem.InventoryItemRequest;
import com.ebay.ebayclientapi.entity.request.inventoryitem.Product;
import com.ebay.ebayclientapi.entity.request.inventoryitem.Request;
import com.ebay.ebayclientapi.entity.request.inventoryitem.ShipToLocationAvailability;
import com.ebay.ebayclientapi.enums.LocaleEnum;
import com.ebay.ebayclientapi.enums.TimeDurationEnum;
import com.ebay.ebayclientapi.exception.CsvException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CsvHelper {

    private static final char SEPARATOR = ',';

    // mapping postion and header in csv file
    private static Map<String, Integer> headerPositionMapping;
    static {
        headerPositionMapping = new HashMap<>();
        // cs-linecode position 1
        headerPositionMapping.put(CsvConstant.CS_LINECODE, 0);
        // part number position 2
        headerPositionMapping.put(CsvConstant.PART_NUMBER, 1);
        // handle time position 17
        headerPositionMapping.put(CsvConstant.HANDLING_TIME, 16);
        // quantity position 11
        headerPositionMapping.put(CsvConstant.QUANTITY, 10);
        // postal code position 8
        headerPositionMapping.put(CsvConstant.POSTAL_CODE, 7);
        // codition position 41
        headerPositionMapping.put(CsvConstant.CONDITION, 40);
        // country position 18
        headerPositionMapping.put(CsvConstant.COUNTRY, 17);
        // brand position 28
        headerPositionMapping.put(CsvConstant.BRAND, 27);
        // description position 33
        headerPositionMapping.put(CsvConstant.DESCRIPTION, 32);
        // epid position 6
        headerPositionMapping.put(CsvConstant.EPID, 5);
        // imgaes postion 14
        headerPositionMapping.put(CsvConstant.IMAGE_URLS, 14);
        // manufacturer part number position 27
        headerPositionMapping.put(CsvConstant.MPN, 26);
        // title position 4
        headerPositionMapping.put(CsvConstant.TITLE, 3);
    }

    private static Map<String, String> countryMapping;
    static {
        countryMapping = new HashMap<>();
        countryMapping.put(CountryConstant.US, LocaleEnum.US.locale);
    }
    
    public static InventoryItemRequest parseCsvFile(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParserBuilder()
                            .withSeparator(SEPARATOR)
                            .withIgnoreQuotations(true)
                            .build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                            .withSkipLines(0)
                            .withCSVParser(csvParser)
                            .build();
            String[] nextLine;
            List<Request> requests = new ArrayList<>();
            while ((nextLine = csvReader.readNext()) != null) {
                Request request = Request.builder()
                    .availability(
                        Availability.builder()
                            .shipToLocationAvailability(
                                ShipToLocationAvailability.builder()
                                    .availabilityDistributions(
                                        Arrays.asList(
                                            AvailabilityDistribution.builder()
                                                .fulfillmentTime(
                                                    FulfillmentTime.builder()
                                                        .unit(TimeDurationEnum.BUSINESS_DAY.toString())
                                                        .value(Integer.valueOf(nextLine[headerPositionMapping.get(CsvConstant.HANDLING_TIME)]))
                                                        .build()
                                                )
                                                .merchantLocationKey(nextLine[headerPositionMapping.get(CsvConstant.POSTAL_CODE)])
                                                .quantity(Integer.valueOf(nextLine[headerPositionMapping.get(CsvConstant.QUANTITY)]))
                                                .build()
                                        )
                                    )
                                    .quantity(Integer.valueOf(nextLine[headerPositionMapping.get(CsvConstant.QUANTITY)]))
                                    .build()
                            )
                            .build()
                    )
                    .condition(nextLine[headerPositionMapping.get(CsvConstant.CONDITION)].toUpperCase())
                    .locale(countryMapping.get(nextLine[headerPositionMapping.get(CsvConstant.COUNTRY)]))
                    .product(
                        Product.builder()
                            .brand(nextLine[headerPositionMapping.get(CsvConstant.BRAND)])
                            .description(nextLine[headerPositionMapping.get(CsvConstant.DESCRIPTION)])
                            .epid(nextLine[headerPositionMapping.get(CsvConstant.EPID)])
                            .imageUrls(Arrays.asList(nextLine[headerPositionMapping.get(CsvConstant.IMAGE_URLS)]))
                            .mpn(nextLine[headerPositionMapping.get(CsvConstant.MPN)])
                            .title(nextLine[headerPositionMapping.get(CsvConstant.TITLE)])
                            .build()
                    )
                    .sku(generateSku(nextLine[headerPositionMapping.get(CsvConstant.CS_LINECODE)], nextLine[headerPositionMapping.get(CsvConstant.PART_NUMBER)]))
                    .build();
                // add request item
                requests.add(request);
            }
            // create IntentoryItemRequest
            InventoryItemRequest inventoryItemRequest = InventoryItemRequest.builder().requests(requests).build();
            return inventoryItemRequest;
        } catch (Exception ex) {
            throw new CsvException("Failed to parse CSV file");
        }
    }

    private static String generateSku(String csLinecode, String partNumber) {
        return csLinecode + ":" + partNumber;
    }
}
