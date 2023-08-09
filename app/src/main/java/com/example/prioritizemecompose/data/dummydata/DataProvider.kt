package com.example.prioritizemecompose.data.dummydata

import com.example.prioritizemecompose.data.db.Priority
import com.example.prioritizemecompose.data.db.Task

object DataProvider {

    private val titles = listOf(
        "Finish Report",
        "Prepare Presentation",
        "Call Client",
        "Update Website Content",
        "Attend Team Meeting",
        "Review Code",
        "Send Invoices",
        "Research Market Trends",
        "Plan Marketing Campaign",
        "Organize Files",
        "Prepare Budget Proposal",
        "Create Product Mockups",
        "Schedule Social Media Posts",
        "Meet with Project Team",
        "Test New Software",
        "Follow Up on Leads",
        "Design Logo",
        "Write Blog Post",
        "Arrange Project Timeline",
        "Review Product Specifications",
        "Arrange Travel Itinerary",
        "Conduct User Survey",
        "Analyze Sales Data",
        "Prepare Training Materials",
        "Coordinate Event Logistics",
        "Brainstorm New Ideas",
        "Optimize Website SEO",
        "Conduct Performance Reviews",
        "Develop App Prototypes"
    )

    private val descriptions = listOf(
        "Complete the final report for the quarterly performance.",
        "Create a PowerPoint presentation for the upcoming client meeting.",
        "Reach out to the client for a follow-up discussion.",
        "Update the content on the website's landing page.",
        "Coordinate logistics for an upcoming company event.",
        "Brainstorm innovative ideas for product improvement.",
        "Optimize the website's SEO for better search rankings.",
        "Conduct performance reviews for team members.",
        "Develop interactive prototypes for the new app.",
        "Test and evaluate the functionality of the new software thoroughly. Document any bugs or issues and work closely with the development team to ensure timely resolution.",
        "Follow up on potential leads and inquiries generated through marketing campaigns and networking events. Provide personalized responses and information tailored to each lead's needs.",
        "Design a new logo for the company rebranding, considering the brand's identity, target audience, and industry trends. Present multiple logo options for feedback and finalize the chosen design.",
        "Write a well-researched and informative blog post about recent industry developments. Use data, statistics, and expert insights to provide valuable content to the blog's readers.",
        "Arrange the timeline for the upcoming project phases, taking into account resource availability, dependencies, and potential risks. Share the timeline with the project stakeholders for feedback.",
        "Review and finalize the product specifications, ensuring they are comprehensive and align with the project's goals. Share the specifications with the development team for implementation.",
        "Plan the travel itinerary for the upcoming business trip, considering flight schedules, accommodation, transportation, and meeting arrangements. Share the itinerary with all relevant team members.",
        "Conduct a detailed survey to gather user feedback on the product's usability, features, and overall satisfaction. Analyze the survey results to identify areas for improvement.",
        "Analyze sales data and identify trends to make data-driven decisions for sales strategies and product improvements. Prepare a comprehensive report to present the findings to the sales team and management.",
        "Prepare training materials for new employees, including onboarding guides, training modules, and presentations. Ensure that the materials cover all essential aspects of the new hire's role.",
        "Coordinate logistics for an upcoming company event, such as a conference or team-building retreat. Secure venues, catering, transportation, and any necessary equipment.",
        "Brainstorm innovative ideas for product improvement, considering user feedback, market trends, and technological advancements. Present the ideas to the product development team for evaluation.",
        "Optimize the website's SEO to improve search engine rankings and organic traffic. Conduct keyword research, update meta tags, and implement on-page SEO best practices.",
        "Conduct performance reviews for team members, providing constructive feedback and setting goals for personal and professional growth. Recognize and reward exceptional performance.",
        "Develop interactive prototypes for the new app, incorporating user-friendly design and intuitive navigation. Test the prototypes with potential users for feedback and validation."
    )

    val tasks = (0..40).map { Task(
        title = titles.random(),
        description = descriptions.random(),
        priority = Priority.values().random()
    ) }
}