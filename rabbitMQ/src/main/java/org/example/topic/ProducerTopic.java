package org.example.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class ProducerTopic {

    public static void main(String[] args) throws Exception {

        String exchangeName = "topic_exchange_name";

        String queueName1 = "topic_queue_name1";
        String queueName2 = "topic_queue_name2";
        String queueName3 = "topic_queue_name3";
        String queueName4 = "topic_queue_name4";

        String key1 = "key_1.key_2.key_3.*";
        String key2 = "key_1.#";
        String key3 = "*.key_2.*.key_4";
        String key4 = "#.key_3.key_4";

        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //服务地址
        factory.setHost("47.97.187.217");
        //账号
        factory.setUsername("admin");
        //密码
        factory.setPassword("123456");
        //端口
        factory.setPort(5672);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        /**
         * 创建交换机
         * 1.交换机名称
         * 2.交换机类型，direct，topic，fanout和headers
         * 3.指定交换机是否需要持久化，如果设置为true，那么交换机的元数据要持久化
         * 4.指定交换机在没有队列绑定时，是否需要珊瑚，设置为false，表示不删除
         * 5.Map<String, Object>类型参数，用来指定交换机其他的一些结构化参数
         */
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, null);

        /**
         * 生成队列
         * 1.队列名称
         * 2.队列是否需要持久化，但是要注意，着里的持久化只是队列名称等这些元数据的持久化，不是队列中消息的持久化
         * 3.标识队列是不是私有的，如果是私有的，只有创建它的应用程序菜被曝光消费消息
         * 4。队列在没有消费者订阅的情况下是否自动删除
         * 5.队列的一些结构化信息，比如声明私信队列，磁盘队列等会用户
         */
        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueDeclare(queueName2, true, false, false, null);
        channel.queueDeclare(queueName3, true, false, false, null);
        channel.queueDeclare(queueName4, true, false, false, null);

        /**
         * 将交换机与队列进行绑定
         * 1.队列名称
         * 2.交换机名称
         * 3.路由键，直连模式下，可以是队列名称
         */
        channel.queueBind(queueName1, exchangeName, key1);
        channel.queueBind(queueName2, exchangeName, key2);
        channel.queueBind(queueName3, exchangeName, key3);
        channel.queueBind(queueName4, exchangeName, key4);

        /**
         * 发送消息
         * 1.发送到哪个交换机
         * 2.路由key
         * 3.其他参数信息
         */
        channel.basicPublish(exchangeName, "key_1.key_2.key_3.key_4.key_5", null, "key1_message".getBytes());

        System.out.println("发送消息成功");
        channel.close();
        connection.close();
    }
}
