package com.example;

import com.example.events.Event;
import com.example.events.EventRepository;
import com.example.events.EventSession;
import com.example.events.EventSessionRepository;
import com.example.registrations.RegistrationRepository;
import com.example.resources.Resource;
import com.example.resources.ResourceRepository;
import com.example.speakers.Speaker;
import com.example.speakers.SpeakerRepository;
import com.example.team.TeamMember;
import com.example.team.TeamMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            EventRepository eventRepository,
            EventSessionRepository sessionRepository,
            SpeakerRepository speakerRepository,
            TeamMemberRepository teamMemberRepository,
            ResourceRepository resourceRepository,
            RegistrationRepository registrationRepository) {

        return args -> {
            if (eventRepository.count() > 0) return;

            // Create Speakers
            Speaker speaker1 = new Speaker();
            speaker1.setName("John Mwangi");
            speaker1.setTitle("Senior Software Engineer at Safaricom");
            speaker1.setBio("John is a passionate Java developer with 10+ years of experience in building enterprise applications. He specializes in Spring Boot and microservices architecture.");
            speaker1.setEmail("john.mwangi@example.com");
            speaker1 = speakerRepository.save(speaker1);

            Speaker speaker2 = new Speaker();
            speaker2.setName("Jane Kipchoge");
            speaker2.setTitle("Tech Lead at Andela");
            speaker2.setBio("Jane leads a team of developers at Andela and is an advocate for clean code and test-driven development. She has spoken at multiple Java conferences across Africa.");
            speaker2.setEmail("jane.kipchoge@example.com");
            speaker2 = speakerRepository.save(speaker2);

            Speaker speaker3 = new Speaker();
            speaker3.setName("Peter Kamau");
            speaker3.setTitle("DevOps Engineer at Microsoft");
            speaker3.setBio("Peter is a DevOps engineer specializing in cloud-native Java applications. He is passionate about CI/CD pipelines and containerization.");
            speaker3.setEmail("peter.kamau@example.com");
            speaker3 = speakerRepository.save(speaker3);

            // Create Events
            Event event1 = new Event();
            event1.setTitle("Kenya JUG March 2025 Meetup");
            event1.setDescription("Join us for an exciting evening of Java talks, networking, and community building. We'll explore the latest features in Spring Boot 3 and discuss best practices for building microservices in Java.");
            event1.setDate(LocalDate.of(2025, 3, 15));
            event1.setTime(LocalTime.of(18, 0));
            event1.setLocation("Safaricom HQ, Westlands, Nairobi");
            event1.setType("MEETUP");
            event1.setStatus("SCHEDULED");
            event1 = eventRepository.save(event1);

            EventSession session1 = new EventSession();
            session1.setEvent(event1);
            session1.setTitle("Spring Boot 3 Features");
            session1.setDescription("Deep dive into the new features of Spring Boot 3 including virtual threads, native compilation, and improved observability.");
            session1.setStartTime(LocalTime.of(18, 0));
            session1.setEndTime(LocalTime.of(18, 45));
            session1.setSpeaker(speaker1);
            sessionRepository.save(session1);

            EventSession session2 = new EventSession();
            session2.setEvent(event1);
            session2.setTitle("Building Microservices with Java");
            session2.setDescription("Learn how to design and implement microservices using Spring Cloud, Docker, and Kubernetes.");
            session2.setStartTime(LocalTime.of(18, 45));
            session2.setEndTime(LocalTime.of(19, 30));
            session2.setSpeaker(speaker2);
            sessionRepository.save(session2);

            Event event2 = new Event();
            event2.setTitle("JavaConnect Kenya 2025");
            event2.setDescription("The biggest Java event in Kenya! A full-day conference featuring world-class speakers, hands-on workshops, and networking opportunities. Topics include Java 21 features, AI/ML with Java, cloud-native development, and more.");
            event2.setDate(LocalDate.of(2025, 6, 20));
            event2.setTime(LocalTime.of(9, 0));
            event2.setLocation("Sarit Centre, Nairobi");
            event2.setType("CONFERENCE");
            event2.setStatus("SCHEDULED");
            event2 = eventRepository.save(event2);

            EventSession session3 = new EventSession();
            session3.setEvent(event2);
            session3.setTitle("Java 21: What's New");
            session3.setDescription("Explore the latest features in Java 21 including virtual threads, pattern matching, and record patterns.");
            session3.setStartTime(LocalTime.of(9, 0));
            session3.setEndTime(LocalTime.of(10, 0));
            session3.setSpeaker(speaker3);
            session3.setRoom("Main Hall");
            sessionRepository.save(session3);

            Event event3 = new Event();
            event3.setTitle("Kenya JUG January 2025 Meetup");
            event3.setDescription("Our first meetup of 2025! We kicked off the year with talks on Java performance tuning and multithreading.");
            event3.setDate(LocalDate.of(2025, 1, 18));
            event3.setTime(LocalTime.of(18, 0));
            event3.setLocation("iHub, Nairobi");
            event3.setType("MEETUP");
            event3.setStatus("COMPLETED");
            eventRepository.save(event3);

            // Create Team Members
            TeamMember member1 = new TeamMember();
            member1.setName("James Ochieng");
            member1.setRole("Founder & Chair");
            member1.setBio("James founded Kenya JUG in 2020 with a vision to bring Java developers together. He is a senior software architect with over 15 years of experience.");
            member1.setEmail("james@kenyajug.org");
            member1.setActive(true);
            teamMemberRepository.save(member1);

            TeamMember member2 = new TeamMember();
            member2.setName("Mary Wanjiku");
            member2.setRole("Community Lead");
            member2.setBio("Mary manages the Kenya JUG community, organizes events, and ensures our members have a great experience. She is passionate about developer education.");
            member2.setEmail("mary@kenyajug.org");
            member2.setActive(true);
            teamMemberRepository.save(member2);

            TeamMember member3 = new TeamMember();
            member3.setName("David Otieno");
            member3.setRole("Events Coordinator");
            member3.setBio("David coordinates all Kenya JUG events, from venue booking to speaker management. He ensures every meetup runs smoothly.");
            member3.setEmail("david@kenyajug.org");
            member3.setActive(true);
            teamMemberRepository.save(member3);

            TeamMember member4 = new TeamMember();
            member4.setName("Grace Akinyi");
            member4.setRole("Social Media Manager");
            member4.setBio("Grace manages Kenya JUG's online presence across all social media platforms. She is skilled in content creation and community engagement.");
            member4.setEmail("grace@kenyajug.org");
            member4.setActive(true);
            teamMemberRepository.save(member4);

            // Create Resources
            Resource r1 = new Resource();
            r1.setTitle("Spring Boot Documentation");
            r1.setDescription("Official Spring Boot documentation with guides, reference, and tutorials for building production-ready applications.");
            r1.setUrl("https://spring.io/projects/spring-boot");
            r1.setResourceType("DOCUMENTATION");
            r1.setCategory("Spring Boot");
            r1.setActive(true);
            resourceRepository.save(r1);

            Resource r2 = new Resource();
            r2.setTitle("Baeldung Java Tutorials");
            r2.setDescription("Comprehensive Java tutorials covering core Java, Spring, and related technologies with practical examples.");
            r2.setUrl("https://www.baeldung.com/");
            r2.setResourceType("TUTORIAL");
            r2.setCategory("Java Core");
            r2.setActive(true);
            resourceRepository.save(r2);

            Resource r3 = new Resource();
            r3.setTitle("Jenkins CI/CD Tool");
            r3.setDescription("Popular open-source automation server for building, deploying, and automating projects. Essential for Java CI/CD pipelines.");
            r3.setUrl("https://www.jenkins.io/");
            r3.setResourceType("TOOL");
            r3.setCategory("DevOps");
            r3.setActive(true);
            resourceRepository.save(r3);

            Resource r4 = new Resource();
            r4.setTitle("Oracle Java Documentation");
            r4.setDescription("Official Java SE documentation including API reference, tutorials, and release notes.");
            r4.setUrl("https://docs.oracle.com/en/java/");
            r4.setResourceType("DOCUMENTATION");
            r4.setCategory("Java Core");
            r4.setActive(true);
            resourceRepository.save(r4);

            Resource r5 = new Resource();
            r5.setTitle("Docker for Java Developers");
            r5.setDescription("Learn how to containerize Java applications with Docker. Covers Dockerfile best practices and multi-stage builds.");
            r5.setUrl("https://docs.docker.com/language/java/");
            r5.setResourceType("TUTORIAL");
            r5.setCategory("DevOps");
            r5.setActive(true);
            resourceRepository.save(r5);

            Resource r6 = new Resource();
            r6.setTitle("IntelliJ IDEA");
            r6.setDescription("The leading Java IDE by JetBrains. Powerful code completion, refactoring, and debugging tools for Java developers.");
            r6.setUrl("https://www.jetbrains.com/idea/");
            r6.setResourceType("TOOL");
            r6.setCategory("IDE");
            r6.setActive(true);
            resourceRepository.save(r6);
        };
    }
}
