package seedu.trackascholar.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * An UI component that displays information of a {@code Applicant}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TrackAScholar level 4</a>
     */

    public final Applicant applicant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label scholarship;
    @FXML
    private Label applicationStatus;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Applicant} and index to display.
     */
    public PersonCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;
        id.setText(displayedIndex + ". ");
        name.setText(applicant.getName().fullName);
        phone.setText(applicant.getPhone().value);
        scholarship.setText(applicant.getScholarship().scholarship);
        applicationStatus.setText(applicant.getApplicationStatus().applicationStatus);
        setApplicationStatusStyling(applicant.getApplicationStatus().applicationStatus);
        email.setText(applicant.getEmail().value);
        applicant.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && applicant.equals(card.applicant);
    }

    public void setApplicationStatusStyling(String status) {
        if (status.equals("accepted")) {
            applicationStatus.getStyleClass().remove("cell_pending_label");
            applicationStatus.getStyleClass().add("cell_accepted_label");
        } else if (status.equals("rejected")) {
            applicationStatus.getStyleClass().remove("cell_pending_label");
            applicationStatus.getStyleClass().add("cell_rejected_label");
        }
    }
}