import java.util.HashMap;
import java.util.Map;

/**
 * Created by CLY on 2015/5/28.
 */
public class KafkaTopologytest {
    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("spout", new KafkaSpouttest(""), 1);
        builder.setBolt("bolt1", new Bolt1(), 2).shuffleGrouping("spout");
        builder.setBolt("bolt2", new Bolt2(), 2).fieldsGrouping("bolt1",new Fields("word"));

        Map conf = new HashMap();
        conf.put(Config.TOPOLOGY_WORKERS, 1);
        conf.put(Config.TOPOLOGY_DEBUG, true);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("my-flume-kafka-storm-topology-integration", conf, builder.createTopology());

        Utils.sleep(1000*60*5); // local cluster test ...
        cluster.shutdown();
    }
}
