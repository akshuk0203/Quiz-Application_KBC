import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KbcController {
    private KbcModel model;
    private KbcView view;
    private int userId;
    private String name;

    public KbcController(KbcModel model, KbcView view) {
        this.model = model;
        this.view = view;
        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }

    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            name = view.getUserName();
            userId = model.insertUser(name);
            if (userId != 0) {
                view.displayMessage("User " + name + " logged in with ID " + userId);
                KbcStartGameView startGameView = new KbcStartGameView(userId, name);
                startGameView.addStartGameButtonListener(new StartGameButtonListener());
                startGameView.addHistoryButtonListener(new HistoryButtonListener());
            } else {
                view.displayMessage("Error inserting user");
            }
        }
    }

    class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            KbcGameView gameView = new KbcGameView();
			KbcGameModel gamemodel= new KbcGameModel();
            new KbcGameController(gamemodel, gameView, userId, name);
            gameView.setVisible(true);
        }
    }

    class HistoryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[][] historyData = model.getUserWinningHistory(userId);
            KbcGameView historyView = new KbcGameView();
            historyView.displayWinningHistory(historyData);
        }
    }
}
