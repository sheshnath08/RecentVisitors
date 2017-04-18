import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class RecentVisitorReducer extends Reducer<Text, LongWritable, Text, LongWritable>{
	
	private Text pid = new Text();
	private LongWritable time = new LongWritable();
    private Map<Text, LongWritable> map = new HashMap<>();
	
  @Override
  public void reduce(Text key, Iterable<LongWritable> values, Context context)
      throws IOException, InterruptedException {

      
	  long max = 0;
//	  Finding most recent time for a user
	  for(LongWritable val:values){
		  if (val.get() > max){
			  max = val.get();
		  }
	  }
	  
	  map.put(new Text(key), new LongWritable(max*-1));
	  
  }
  
  @Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
	  
	  Map<Text, LongWritable> sortedMap = MapUtils.sortByValue(map);
	  int counter = 0;
      for (Text key : sortedMap.keySet()) {
          if (counter++ == 100) {
              break;
          }
          time.set(sortedMap.get(key).get()*(-1));
          pid=key;
          context.write(pid, time);
      }
	}
  
  
}
  