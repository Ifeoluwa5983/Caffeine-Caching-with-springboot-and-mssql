package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.commands.UpdateKYCDetailsCommand;
import com.caffeine.Caffeine.Caching.events.UpdateKYCDetailEvent;
import com.caffeine.Caffeine.Caching.models.KYCDetail;

import junit.framework.TestCase;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
public class AxonUpdateTest extends TestCase {


    FixtureConfiguration fixture;
    Date date;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture(KYCDetail.class);
        date = new Date();
    }

    @Test
    public void updateKYCDetails() {
        fixture.given()
                .when(new UpdateKYCDetailsCommand(3L, "Pdf", "naira", "nice",LocalDate.of(2021, 9, 2) , LocalDate.of(2021, 9, 2), true, true, "Ifeoluwa", LocalDate.of(2021, 9, 2), (byte) 12, 1L))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UpdateKYCDetailEvent(3L, "Pdf", "naira", "nice", LocalDate.of(2021, 9, 2), LocalDate.of(2021, 9, 2), true, true, "Ifeoluwa", LocalDate.of(2021, 9, 2), (byte) 12, 1L));

    }
    
}
