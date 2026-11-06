package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "join", layout = MainLayout.class)
@PageTitle("Join — Kenya JUG")
@AnonymousAllowed
public class JoinView extends StaticPage {

    private static final String SUCCESS_BANNER = """
            <div class="wrap pt-6">
              <div role="status" class="border-l-[3px] border-green bg-white px-5 py-4">
                <p class="font-semibold">Thanks for joining Kenya JUG.</p>
                <p class="mt-1 text-small text-grey-600">
                  We’ve received your details and will be in touch with next steps.
                </p>
              </div>
            </div>
            """;

    public JoinView() {
        super("join");

        VaadinRequest request = VaadinRequest.getCurrent();
        if (request != null && "1".equals(request.getParameter("submitted"))) {
            getElement().setProperty("innerHTML", SUCCESS_BANNER + loadPage("join"));
        }
    }
}
