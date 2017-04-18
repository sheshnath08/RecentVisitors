import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.json.*;

public class RecentVisitorMapper extends Mapper<Object, Text, Text, LongWritable> {
	
	Text pid = new Text();
	LongWritable time = new LongWritable();


  @Override
  public void map(Object key,Text  value, Context context)
      throws IOException, InterruptedException {
	  
	  String line = value.toString();
	  JSONObject jsonLine = new JSONObject(line);
//	  parsing json lines
	  String id = jsonLine.getString("pid");
	  Long timeStamp = jsonLine.getLong("time");
	  
	  pid.set(id);
	  time.set(timeStamp);
//	  writing output
	  context.write(pid, time);

  }
  
}
