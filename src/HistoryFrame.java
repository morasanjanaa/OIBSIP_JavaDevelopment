import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryFrame extends JFrame {
    private final User user;

    public HistoryFrame(User user) {
        this.user = user;
        setTitle("Transaction History - User " + user.getUserId());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        List<String> history = user.getHistory();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = history.size()-1; i >= 0; i--) {
            listModel.addElement(history.get(i));
        }
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        add(scrollPane, BorderLayout.CENTER);
    }
}
