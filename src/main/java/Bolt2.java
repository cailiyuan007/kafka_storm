import java.util.HashMap;
import java.util.Map;

/**
 * Created by CLY on 2015/5/28.
 */
public class Bolt2 extends BaseBasicBolt {
    Map<String, Integer> counts = new HashMap<String, Integer>();


    public void execute(Tuple tuple, BasicOutputCollector collector) {
        String msg = tuple.getString(0);
        msg = msg + "bolt2";
        System.out.println("对消息加工第2次---------->"+msg);
        collector.emit(new Values(msg,1));
    }


    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}
