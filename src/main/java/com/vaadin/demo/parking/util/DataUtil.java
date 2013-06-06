package com.vaadin.demo.parking.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.demo.parking.ParkingUI;
import com.vaadin.demo.parking.widgetset.client.model.Shift;
import com.vaadin.demo.parking.widgetset.client.model.Ticket;
import com.vaadin.demo.parking.widgetset.client.model.Violation;

public class DataUtil {

    private static final int RANDOM_SHIFT_COUNT = 700;
    private static final int HOUR_IN_MILLIS = 1000 * 60 * 60;
    private static final List<String> NAMES = Arrays.asList("John Doe",
            "Jane Doe");

    /**
     * Generate a collection of random shifts.
     * 
     * @return
     */
    public static Collection<Shift> generateRandomShifts() {
        Random random = new Random();

        Collection<Shift> result = Lists.newArrayList();
        for (int i = 0; i < RANDOM_SHIFT_COUNT; i++) {
            Shift shift = new Shift();

            shift.setArea("ABCDEFG".charAt(random.nextInt(5))
                    + String.valueOf(random.nextInt(30)));

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, random.nextInt(1000));
            cal.set(Calendar.MINUTE, 0);
            shift.setDate(cal.getTime());

            shift.setDurationMillis(HOUR_IN_MILLIS + random.nextInt(8)
                    * HOUR_IN_MILLIS);

            shift.setName(NAMES.get(random.nextInt(NAMES.size())));

            result.add(shift);
        }
        return result;
    }

    private static final int RANDOM_TICKETS_COUNT = 300;

    // TODO: Dummy images/data needed
    public static Collection<Ticket> generateRandomTickets() {
        Random random = new Random();

        Collection<Ticket> result = Lists.newArrayList();
        for (int i = 0; i < RANDOM_TICKETS_COUNT; i++) {
            Ticket ticket = new Ticket();

            ticket.setNotes("Testing" + i);
            ticket.setImageData("https://dl.dropboxusercontent.com/u/1966780/Parking_violation_Vaughan_Mills.jpg");
            ticket.setRegisterPlateNumber("ABC-" + i + "" + (i + 1) + ""
                    + (i + 2));

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, -random.nextInt(1000));
            cal.set(Calendar.MINUTE, 0);
            ticket.setTimeStamp(cal.getTime());

            ticket.setViolation(i % 2 == 0 ? Violation.HANDICAPPED_ZONE
                    : Violation.PROHIBITED_SPACE);
            result.add(ticket);
        }
        return result;
    }

    public static void persistTicket(final Ticket ticket) {
        ParkingUI.getTicketContainer().addItem(ticket);
    }
}
