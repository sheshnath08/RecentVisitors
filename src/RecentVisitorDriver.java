import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class RecentVisitorDriver {

  public static void main(String[] args) throws Exception {

	  Configuration conf = new Configuration();
	  
	  String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
      if (otherArgs.length != 2) {
          System.err.println("Usage: Recent Visitor <in> <out>");
          System.exit(2);
      }
	  
     Job job = Job.getInstance(conf, "Recent 100 Visitors");
     job.setJarByClass(RecentVisitorDriver.class);
     job.setMapperClass(RecentVisitorMapper.class);
     job.setCombinerClass(RecentVisitorReducer.class);
     job.setReducerClass(RecentVisitorReducer.class);
    
     job.setOutputKeyClass(Text.class);
     job.setOutputValueClass(LongWritable.class);
    
     job.setNumReduceTasks(1);
     // Add external jar file path here
     job.addFileToClassPath(new Path("json"));
    
     FileInputFormat.addInputPath(job, new Path(args[0]));
     FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
     System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
