package ru.teamsource.bankruptservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class HadoopPersonProcessing {
    @Value("person-data-process-input-path")
    String personDataProcessInputPath;
    @Value("person-data-process-output-path")
    String personDataProcessOutputPath;
    private final Configuration conf;

    public static class PersonMapper extends Mapper<LongWritable, Text, Text, Text> {
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split(",");
            String inn = fields[0];
            String recordType = fields[1];
            context.write(new Text(inn), new Text(recordType + "," + value));
        }
    }

    public static class PersonReducer extends Reducer<Text, Text, Text, NullWritable> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int totalCreditScore = 0;
            int totalApplications = 0;
            double totalCreditRequested = 0;

            Iterator<Text> it = values.iterator();
            while (it.hasNext()) {
                String record = it.next().toString();
                String[] parts = record.split(",");
                String recordType = parts[0];

                switch (recordType) {
                    case "person":
                        break;
                    case "credit_history":
                        int creditScore = Integer.parseInt(parts[3]);
                        totalCreditScore += creditScore;
                        break;
                    case "application_history":
                        totalApplications++;
                        break;
                    case "credit_requests_history":
                        double amountRequested = Double.parseDouble(parts[3]);
                        totalCreditRequested += amountRequested;
                        break;
                }
            }

            String result = key.toString() + "," + totalCreditScore + "," + totalApplications + "," + totalCreditRequested;
            context.write(new Text(result), NullWritable.get());
        }
    }

    @SneakyThrows
    public void runPersonDataProcess() {
        Path input = new Path(personDataProcessInputPath);
        Path output = new Path(personDataProcessOutputPath);
        Job job = Job.getInstance(conf, "Person Data Processing");
        job.setJarByClass(HadoopPersonProcessing.class);
        job.setMapperClass(PersonMapper.class);
        job.setReducerClass(PersonReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, input);
        FileOutputFormat.setOutputPath(job, output);
        job.waitForCompletion(true);
    }
}