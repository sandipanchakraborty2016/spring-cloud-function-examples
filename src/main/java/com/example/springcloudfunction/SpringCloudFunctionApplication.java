package com.example.springcloudfunction;

import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringCloudFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFunctionApplication.class, args);
    }

    List<TollStation> tollStations;

    public SpringCloudFunctionApplication() {
        tollStations = new ArrayList<>();
        tollStations.add(new TollStation("1", "name1", "address1"));
        tollStations.add(new TollStation("2", "name2", "address2"));
        tollStations.add(new TollStation("3", "name3", "address3"));
    }

    @Bean
    public Function<String, TollStation> retrieveStation() {
        return value -> {
            System.out.println("requested=" + value);
            return tollStations.stream()
                    .filter(toll -> value.equals(toll.getId())).findFirst().orElse(null);
        };
    }

    @Bean
    public Consumer<TollRecord> processRecord() {
        return value -> {
            System.out.println("Recieved" + value.getStationId());
        };
    }

    @Bean
    public Function<TollRecord, Mono<Void>> processTollRecord() {
        return value -> {
            System.out.println("Received reactive " + value.getStationId());
            System.out.println("Received reactive " + value.getLicensePlate());
            return Mono.empty();
        };
    }

    @Bean
    public Consumer<Flux<TollRecord>> processListOfTollRecord() {
        return value -> {
            value.subscribe(toll -> System.out.println(toll.getStationId()));
            value.subscribe(toll -> System.out.println(toll.getLicensePlate()));
        };
    }

    @Bean
    public Supplier<Flux<TollStation>> getTollStations() {
        System.out.println("Flux for Suppliers");
        return () -> Flux.fromIterable(tollStations);
    }
}
