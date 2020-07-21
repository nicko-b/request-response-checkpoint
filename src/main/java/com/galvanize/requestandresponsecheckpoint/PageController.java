package com.galvanize.requestandresponsecheckpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.replaceAll;

@RestController
public class PageController {


    @GetMapping("/camelize")
    public String getSnakeBeGone(
            @RequestParam(value = "original") String original,
            @RequestParam(value = "initialCap", required = false) boolean initialCap) {

        StringBuilder nameBuilder = new StringBuilder(original.length());

        for (char c : original.toCharArray()) {
            if (c == '_') {
                initialCap = true;
                continue;
            }
            if (initialCap) {
                nameBuilder.append(Character.toUpperCase(c));
            } else {
                nameBuilder.append(c);
            }
            initialCap = false;
        }
        return nameBuilder.toString();
    }

    @GetMapping("/redact")
    public String getSwearsOut(
            @RequestParam(value = "original") List<String> original,
            @RequestParam(value = "badWord", required = false) List<String> badWord) {

        if (original.contains(badWord))

            for (int i = 0; i < original.size(); i++) {
                if(Objects.equals(original.get(i), badWord)) {
                    original.set(i, "*");
                    return String.valueOf(original);
                }
        }
        return String.valueOf(original);
    }
}







