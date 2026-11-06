package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles the static join form rendered on {@code /join}.
 *
 * <p>The public site is anonymous, so the submission is accepted and acknowledged
 * without requiring an account. Keeping the handler server-side means the form
 * keeps working without any client-side JavaScript.</p>
 */
@Controller
public class JoinController {

    private static final Logger log = LoggerFactory.getLogger(JoinController.class);

    @PostMapping("/api/join")
    public String join(@RequestParam String fullName,
                       @RequestParam String email,
                       @RequestParam(required = false) String joiningAs,
                       @RequestParam(required = false) String location,
                       @RequestParam(required = false) String website,
                       @RequestParam(required = false) String agreesToCodeOfConduct) {
        // Hidden honeypot field: silently accept without processing bots.
        if (website == null || website.isBlank()) {
            log.info("New Kenya JUG join request: {} <{}> ({}){}",
                    fullName, email, joiningAs,
                    location == null || location.isBlank() ? "" : " from " + location);
        }
        return "redirect:/join?submitted=1";
    }
}
