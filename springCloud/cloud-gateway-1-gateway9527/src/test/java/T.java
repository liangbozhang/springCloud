import java.time.ZonedDateTime;

public class T {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);
        // 断言时间 after、before、between(2个)：2023-11-01T16:13:40.444+08:00[Asia/Shanghai]
    }
}
