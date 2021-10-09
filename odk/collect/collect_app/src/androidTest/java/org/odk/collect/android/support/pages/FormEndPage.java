package org.odk.collect.android.support.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.odk.collect.android.R;

public class FormEndPage extends Page<FormEndPage> {

    private final String formName;

    public FormEndPage(String formName) {
        this.formName = formName;
    }

    @Override
    public FormEndPage assertOnPage() {
        onView(withText(getTranslatedString(R.string.save_enter_data_description, formName))).check(matches(isDisplayed()));
        return this;
    }

    public <D extends Page<D>> D clickSaveAsDraft(D destination) {
        clickOnString(R.string.save_as_draft);
        return destination.assertOnPage();
    }

    public MainMenuPage clickSaveAsDraft() {
        clickOnString(R.string.save_as_draft);
        return new MainMenuPage().assertOnPage();
    }

    public <D extends Page<D>> D clickFinalize(D destination) {
        clickOnString(R.string.finalize);
        return destination.assertOnPage();
    }

    public MainMenuPage clickFinalize() {
        return clickFinalize(new MainMenuPage());
    }

    public MainMenuPage clickSend() {
        clickOnString(R.string.send);
        return new MainMenuPage().assertOnPage();
    }

    public FormMapPage clickSaveAndExitBackToMap() {
        return clickSaveAsDraft(new FormMapPage(formName));
    }

    public FormEntryPage clickSaveAndExitWithError(String errorText) {
        clickOnString(R.string.finalize);
        assertConstraintDisplayed(errorText);
        return new FormEntryPage(formName).assertOnPage();
    }

    public ChangesReasonPromptPage clickSaveAndExitWithChangesReasonPrompt() {
        return clickFinalize(new ChangesReasonPromptPage(formName));
    }

    public FormHierarchyPage clickGoToArrow() {
        onView(withId(R.id.menu_goto)).perform(click());
        return new FormHierarchyPage(formName);
    }

    public FormEntryPage swipeToPreviousQuestion(String questionText) {
        return new FormEntryPage(formName).swipeToPreviousQuestion(questionText);
    }

    public FormEntryPage swipeToPreviousQuestion(String questionText, boolean isRequired) {
        return new FormEntryPage(formName).swipeToPreviousQuestion(questionText, isRequired);
    }

    public FormEndPage clickOptionsIcon() {
        return clickOptionsIcon(R.string.project_settings);
    }

    public FormEntryPage assertConstraintDisplayed(String constraintText) {
        FormEntryPage formEntryPage = new FormEntryPage(formName);
        return formEntryPage.assertConstraintDisplayed(constraintText);
    }
}
