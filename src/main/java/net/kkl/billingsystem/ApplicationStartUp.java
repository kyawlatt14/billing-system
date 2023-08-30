package net.kkl.billingsystem;

import net.kkl.billingsystem.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class ApplicationStartUp implements CommandLineRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port;

    @Value("${spring.profiles.active:}")
    private String active;

    @Override
    public void run(String... args) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String str = "\n------------- " + this.applicationName + " (" + this.active + ") by " + DateUtils.getNowString() + " -------------\n" +
                "\t- Local:   http://localhost:" + this.port + "\n" +
                "\t- Local2:  http://127.0.0.1:" + this.port + "\n" +
                "\t- Network: https://" + ip + ":" + this.port +
                "\n------------- " + this.applicationName + " (" + this.active + ") by " + DateUtils.getNowString() + " -------------\n";
        System.out.println(str);
    }

}
