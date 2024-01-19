package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.BoardWorkSpaceFragment;
import ui.fragments.CardWorkSpaceFragment;
import utils.Month;

import java.util.ArrayList;
import java.util.List;

public class BoardPage extends BasePage {

    @Getter
    private final BoardWorkSpaceFragment boardWorkSpaceFragment = new BoardWorkSpaceFragment();
    @Getter
    private final CardWorkSpaceFragment cardFragment = new CardWorkSpaceFragment();

    public boolean isCompleteCheckboxDates() {
        return Boolean.parseBoolean(cardFragment.getCheckboxCompleteDates().getAttribute("aria-checked"));
    }

    private List<String> getSetMonth(String date) {
        date = cardFragment.getDataAboutDueDate().getText();
        String[] parts = date.split(" - ");
        String[] startMonthInfo = parts[0].split(" ");
        String[] endMonthInfo = parts[1].split(" ");

        List<String> result = new ArrayList<>();
        result.add(startMonthInfo[0]);
        result.add(startMonthInfo[1]);
        result.add(endMonthInfo[0]);
        result.add(endMonthInfo[1]);

        return result;
    }

    public boolean isValidDates(int due, int start,Month monthDue,Month monthStart) {
        List<String> aa = getSetMonth(getCardFragment().getDataAboutDueDate().getText());
        Month dueMonth = Month.valueOf(aa.get(2));
        int dueDay = Integer.parseInt(aa.get(3));
        Month startMonth = Month.valueOf(aa.get(0));
        int startDay = Integer.parseInt(aa.get(1));

        return dueMonth == monthDue && dueDay == due && startMonth == monthStart && startDay == start;
    }
}