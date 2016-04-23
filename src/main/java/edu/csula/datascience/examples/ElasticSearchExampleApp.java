package edu.csula.datascience.examples;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * A quick elastic search example app
 *
 * It will parse the csv file from the resource folder under main and send these
 * data to elastic search instance running locally
 *
 * After that we will be using elastic search to do full text search
 */
public class ElasticSearchExampleApp {
    public static void main(String[] args) throws URISyntaxException {
        Node node = nodeBuilder().settings(Settings.builder()
            .put("path.home", "elasticsearch-data")).node();
        Client client = node.client();

        File csv = new File(
            ClassLoader.getSystemResource("GlobalLandTemperaturesByState.csv")
                .toURI()
        );

        try {
            CSVParser parser = CSVParser.parse(csv, Charset.defaultCharset(), CSVFormat.EXCEL.withHeader());

            parser.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Temperature {
        final LocalDateTime date;
        final double averageTemperature;
        final String state;
        final String country;

        public Temperature(LocalDateTime date, double averageTemperature, String state, String country) {
            this.date = date;
            this.averageTemperature = averageTemperature;
            this.state = state;
            this.country = country;
        }
    }
}
