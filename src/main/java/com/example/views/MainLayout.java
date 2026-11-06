package com.example.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * Public site chrome, reproduced from the Kenya JUG reference site.
 *
 * <p>The header and footer are written as static markup so they match the target
 * exactly; only the active navigation state is computed per route.</p>
 */
@AnonymousAllowed
public class MainLayout extends Div implements RouterLayout, BeforeEnterObserver {

    private String currentPath = "";

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        currentPath = event.getLocation().getPath();
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        removeAll();
        addClassName("kj-app");

        Div announcement = new Div();
        announcement.getElement().setAttribute("role", "region");
        announcement.getElement().setAttribute("aria-label", "Announcement");
        announcement.getElement().setAttribute("class", "bg-ink text-cream");
        announcement.getElement().setProperty("innerHTML", """
                <div class="wrap flex min-h-[40px] items-center gap-3 py-2 text-small">
                  <span class="tag whitespace-nowrap text-red-300">
                    Next up
                  </span>
                  <span>
                    Meetups and RSVPs are on Zenlipa.
                  </span>
                  <a href="https://kenyanjug.zenlipa.co.ke/" class="inline-flex min-h-[24px] items-center whitespace-nowrap underline underline-offset-4">
                    See events →
                  </a>
                </div>
                """);

        Header header = new Header();
        header.getElement().setAttribute("class", "relative border-b border-line bg-cream");
        header.getElement().setProperty("innerHTML", buildHeader());

        Main main;
        if (content instanceof Main routedMain) {
            routedMain.addClassName("flex-1");
            main = routedMain;
        } else if (content instanceof Component component) {
            main = new Main();
            main.addClassName("flex-1");
            main.add(component);
        } else {
            main = new Main();
            main.addClassName("flex-1");
            main.getElement().appendChild(content.getElement());
        }

        Footer footer = new Footer();
        footer.getElement().setAttribute("class", "bg-ink text-cream");
        footer.getElement().setProperty("innerHTML", buildFooter());

        add(announcement, header, main, footer);

        // Exact markup means the mobile menu toggle is plain HTML. Wire it up
        // after the fragment has been inserted into the DOM.
        getElement().executeJs("""
                const button = this.querySelector('button[aria-controls="mobile-menu"]');
                const menu = this.querySelector('#mobile-menu');
                if (button && menu) {
                  button.addEventListener('click', () => {
                    const expanded = button.getAttribute('aria-expanded') === 'true';
                    button.setAttribute('aria-expanded', String(!expanded));
                    menu.hidden = !expanded;
                  });
                }
                """);
    }

    private String buildHeader() {
        String home = isActive("") ? " aria-current=\"page\"" : "";
        String about = isActive("about") ? " aria-current=\"page\"" : "";
        String events = isActive("events") ? " aria-current=\"page\"" : "";
        String community = isActive("community") ? " aria-current=\"page\"" : "";
        String partnerships = isActive("partnerships") ? " aria-current=\"page\"" : "";
        String resources = isActive("resources") ? " aria-current=\"page\"" : "";

        return """
                <div class="wrap flex min-h-[72px] items-center justify-between gap-5">
                  <a class="flex items-center gap-3 no-underline" aria-label="Kenya JUG home" href="/">
                    <img alt="" width="44" height="44" class="size-[44px] rounded-full" src="/images/logo.jpg">
                    <span class="font-display text-[20px] leading-none font-semibold tracking-[-0.01em] whitespace-nowrap">
                      Kenya JUG
                    </span>
                  </a>
                  <nav aria-label="Main" class="hidden lg:block">
                    <ul class="flex gap-6">
                      <li><a%1$s class="border-b-2 border-transparent py-2 font-medium no-underline transition-colors hover:border-ink aria-[current=page]:border-red" href="/">Home</a></li>
                      <li><a%2$s class="border-b-2 border-transparent py-2 font-medium no-underline transition-colors hover:border-ink aria-[current=page]:border-red" href="/about">About</a></li>
                      <li><a%3$s class="border-b-2 border-transparent py-2 font-medium no-underline transition-colors hover:border-ink aria-[current=page]:border-red" href="/events">Events</a></li>
                      <li><a%4$s class="border-b-2 border-transparent py-2 font-medium no-underline transition-colors hover:border-ink aria-[current=page]:border-red" href="/community">Community</a></li>
                      <li><a%5$s class="border-b-2 border-transparent py-2 font-medium no-underline transition-colors hover:border-ink aria-[current=page]:border-red" href="/partnerships">Partnerships</a></li>
                      <li><a%6$s class="border-b-2 border-transparent py-2 font-medium no-underline transition-colors hover:border-ink aria-[current=page]:border-red" href="/resources">Resources</a></li>
                    </ul>
                  </nav>
                  <div class="hidden lg:block">
                    <a class="btn btn-primary" href="/join">
                      Join Kenya JUG
                    </a>
                  </div>
                  <div class="lg:hidden">
                    <button type="button" aria-expanded="false" aria-controls="mobile-menu" class="tag inline-flex min-h-[44px] items-center rounded-sm border-[1.5px] border-ink px-3">
                      Menu
                    </button>
                    <div id="mobile-menu" hidden class="absolute inset-x-0 top-full z-10 bg-ink px-[var(--layout-margin)] pt-5 pb-7 text-cream">
                      <nav aria-label="Main">
                        <ul class="my-6 grid">
                          <li><a%1$s class="block border-b border-line-inverse py-4 font-display text-[28px] leading-[1.2] font-semibold no-underline aria-[current=page]:text-red-300" href="/">Home</a></li>
                          <li><a%2$s class="block border-b border-line-inverse py-4 font-display text-[28px] leading-[1.2] font-semibold no-underline aria-[current=page]:text-red-300" href="/about">About</a></li>
                          <li><a%3$s class="block border-b border-line-inverse py-4 font-display text-[28px] leading-[1.2] font-semibold no-underline aria-[current=page]:text-red-300" href="/events">Events</a></li>
                          <li><a%4$s class="block border-b border-line-inverse py-4 font-display text-[28px] leading-[1.2] font-semibold no-underline aria-[current=page]:text-red-300" href="/community">Community</a></li>
                          <li><a%5$s class="block border-b border-line-inverse py-4 font-display text-[28px] leading-[1.2] font-semibold no-underline aria-[current=page]:text-red-300" href="/partnerships">Partnerships</a></li>
                          <li><a%6$s class="block border-b border-line-inverse py-4 font-display text-[28px] leading-[1.2] font-semibold no-underline aria-[current=page]:text-red-300" href="/resources">Resources</a></li>
                        </ul>
                      </nav>
                      <a class="btn btn-on-ink w-full" href="/join">
                        Join Kenya JUG
                      </a>
                    </div>
                  </div>
                </div>
                """.formatted(home, about, events, community, partnerships, resources);
    }

    private String buildFooter() {
        return """
                <div class="stripes" aria-hidden="true"></div>
                <div class="wrap">
                  <div class="grid grid-cols-2 gap-x-4 gap-y-6 pt-8 pb-7 md:grid-cols-[2fr_1fr_1fr_1fr_1.4fr]">
                    <div class="col-span-full md:col-span-1">
                      <img alt="Kenya JUG logo" loading="lazy" width="72" height="72" class="size-[72px] rounded-full" src="/images/logo.jpg">
                      <p class="mt-4 max-w-[32ch] text-grey-400">
                        The Java User Group for Kenya.
                      </p>
                    </div>
                    <div>
                      <h2 class="tag mb-4 text-grey-400">Community</h2>
                      <ul class="grid gap-3">
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/about">About</a></li>
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/community">Chapters</a></li>
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/join">Join</a></li>
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/code-of-conduct">Code of Conduct</a></li>
                      </ul>
                    </div>
                    <div>
                      <h2 class="tag mb-4 text-grey-400">Learn</h2>
                      <ul class="grid gap-3">
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/events">Events</a></li>
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/resources">Resources</a></li>
                        <li><a class="no-underline hover:underline hover:underline-offset-4" href="/partnerships">Partnerships</a></li>
                      </ul>
                    </div>
                    <div>
                      <h2 class="tag mb-4 text-grey-400">Follow</h2>
                      <ul class="grid gap-3">
                        <li><a href="https://x.com/kenyanJug" class="break-words no-underline hover:underline hover:underline-offset-4">X</a></li>
                        <li><a href="https://www.linkedin.com/company/kenya-java-user-group" class="break-words no-underline hover:underline hover:underline-offset-4">LinkedIn</a></li>
                        <li><a href="https://www.youtube.com/@KenyaJug" class="break-words no-underline hover:underline hover:underline-offset-4">YouTube</a></li>
                      </ul>
                    </div>
                    <div>
                      <h2 class="tag mb-4 text-grey-400">Contact</h2>
                      <ul class="grid gap-3">
                        <li><a href="mailto:kenyajug@gmail.com" class="break-words no-underline hover:underline hover:underline-offset-4">kenyajug@gmail.com</a></li>
                        <li><a href="tel:+254792188181" class="break-words no-underline hover:underline hover:underline-offset-4">+254 792 188 181</a></li>
                        <li><a href="https://kenyanjug.zenlipa.co.ke/" class="break-words no-underline hover:underline hover:underline-offset-4">Events and RSVP</a></li>
                        <li><a href="https://chat.whatsapp.com/FpIjTghbH8Q2HgDIpkw8wA" class="break-words no-underline hover:underline hover:underline-offset-4">WhatsApp community</a></li>
                      </ul>
                    </div>
                  </div>
                  <div class="flex flex-wrap justify-between gap-4 border-t border-line-inverse py-5 text-small text-grey-400">
                    <span>© 2026 Kenya Java User Group</span>
                    <span>Java is a registered trademark of Oracle and/or its affiliates.</span>
                  </div>
                </div>
                """;
    }

    private boolean isActive(String route) {
        String path = currentPath == null ? "" : currentPath;
        if (route.isEmpty()) {
            return path.isEmpty() || path.equals("home");
        }
        return path.equals(route) || path.startsWith(route + "/");
    }
}
