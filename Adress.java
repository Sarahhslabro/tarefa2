import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Adress extends JFrame {
    private Person currentEntry;
    private PersonQueries personQueries;
    private List<Person> results;
    private int numberOfEntries = 0;
    private int currentEntryIndex;

    private JButton browseButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton queryButton;
    private JButton insertButton;

    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;

    private JLabel ofLabel;
    private JTextField indexTextField;
    private JTextField maxTextField;

    private JPanel navigatePanel;
    private JPanel displayPanel;
    private JPanel queryPanel;
    private JLabel queryLabel;
    private JTextField queryTextField;

    // Construtor sem argumento
    public Adress() {
        super("Address Book");

        personQueries = new PersonQueries();

        // Cria a GUI
        navigatePanel = new JPanel();
        previousButton = new JButton("Previous");
        indexTextField = new JTextField(2);
        ofLabel = new JLabel("of");
        maxTextField = new JTextField(2);
        nextButton = new JButton("Next");

        displayPanel = new JPanel();
        idLabel = new JLabel("Address ID:");
        idTextField = new JTextField(10);
        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField(10);
        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField(10);
        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField(10);
        phoneLabel = new JLabel("Phone Number:");
        phoneTextField = new JTextField(10);

        queryPanel = new JPanel();
        queryLabel = new JLabel("Last Name:");
        queryTextField = new JTextField(10);
        queryButton = new JButton("Find");

        browseButton = new JButton("Browse All Entries");
        insertButton = new JButton("Insert New Entry");

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setSize(400, 300);
        setResizable(false);

        // Navegação
        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.X_AXIS));
        previousButton.setEnabled(false);
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });
        navigatePanel.add(previousButton);
        navigatePanel.add(Box.createHorizontalStrut(10));

        indexTextField.setHorizontalAlignment(JTextField.CENTER);
        indexTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                indexTextFieldActionPerformed(evt);
            }
        });
        navigatePanel.add(indexTextField);
        navigatePanel.add(Box.createHorizontalStrut(10));

        navigatePanel.add(ofLabel);
        navigatePanel.add(Box.createHorizontalStrut(10));

        maxTextField.setHorizontalAlignment(JTextField.CENTER);
        maxTextField.setEditable(false);
        navigatePanel.add(maxTextField);
        navigatePanel.add(Box.createHorizontalStrut(10));

        nextButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        navigatePanel.add(nextButton);
        add(navigatePanel);

        // Painel de exibição
        displayPanel.setLayout(new GridLayout(5, 2, 4, 4));
        displayPanel.add(idLabel);
        idTextField.setEditable(false);
        displayPanel.add(idTextField);
        displayPanel.add(firstNameLabel);
        displayPanel.add(firstNameTextField);
        displayPanel.add(lastNameLabel);
        displayPanel.add(lastNameTextField);
        displayPanel.add(emailLabel);
        displayPanel.add(emailTextField);
        displayPanel.add(phoneLabel);
        displayPanel.add(phoneTextField);
        add(displayPanel);

        // Painel de consulta
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.X_AXIS));
        queryPanel.setBorder(BorderFactory.createTitledBorder("Find an entry by last name"));
        queryPanel.add(Box.createHorizontalStrut(5));
        queryPanel.add(queryLabel);
        queryPanel.add(Box.createHorizontalStrut(10));
        queryPanel.add(queryTextField);
        queryPanel.add(Box.createHorizontalStrut(10));
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                queryButtonActionPerformed(evt);
            }
        });
        queryPanel.add(queryButton);
        queryPanel.add(Box.createHorizontalStrut(5));
        add(queryPanel);

        add(browseButton);
        add(insertButton);

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                personQueries.close();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void previousButtonActionPerformed(ActionEvent evt) {
        currentEntryIndex--;
        if (currentEntryIndex < 0) {
            currentEntryIndex = numberOfEntries - 1;
        }
        indexTextField.setText("" + (currentEntryIndex + 1));
        indexTextFieldActionPerformed(evt);
    }

    private void nextButtonActionPerformed(ActionEvent evt) {
        currentEntryIndex++;
        if (currentEntryIndex >= numberOfEntries) {
            currentEntryIndex = 0;
        }
        indexTextField.setText("" + (currentEntryIndex + 1));
        indexTextFieldActionPerformed(evt);
    }

    private void queryButtonActionPerformed(ActionEvent evt) {
        results = personQueries.getPeopleByLastName(queryTextField.getText());
        numberOfEntries = results.size();

        if (numberOfEntries != 0) {
            currentEntryIndex = 0;
            currentEntry = results.get(currentEntryIndex);

            idTextField.setText("" + currentEntry.getadressId());
            firstNameTextField.setText(currentEntry.getfirstName());
            lastNameTextField.setText(currentEntry.getlastName());
            emailTextField.setText(currentEntry.getEmail());
            phoneTextField.setText(currentEntry.getPhoneNumber());
            maxTextField.setText("" + numberOfEntries);
            indexTextField.setText("" + (currentEntryIndex + 1));
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
        }
        else {
            browseButtonActionPerformed(evt);
        }
    }

    private void indexTextFieldActionPerformed(ActionEvent evt) {
        currentEntryIndex = Integer.parseInt(indexTextField.getText()) - 1;
        if (numberOfEntries != 0 && currentEntryIndex < numberOfEntries) {
            currentEntry = results.get(currentEntryIndex);
            idTextField.setText("" + currentEntry.getadressId());
            firstNameTextField.setText(currentEntry.getfirstName());
            lastNameTextField.setText(currentEntry.getlastName());
            emailTextField.setText(currentEntry.getEmail());
            phoneTextField.setText(currentEntry.getPhoneNumber());
            maxTextField.setText("" + numberOfEntries);
            indexTextField.setText("" + (currentEntryIndex + 1));
        }
    }

    private void browseButtonActionPerformed(ActionEvent evt) {
        try {
            results = personQueries.getAllPeople();
            numberOfEntries = results.size();

            if (numberOfEntries != 0) {
                currentEntryIndex = 0;
                currentEntry = results.get(currentEntryIndex);
                idTextField.setText("" + currentEntry.getadressId());
                firstNameTextField.setText(currentEntry.getfirstName());
                lastNameTextField.setText(currentEntry.getlastName());
                emailTextField.setText(currentEntry.getEmail());
                phoneTextField.setText(currentEntry.getPhoneNumber());
                maxTextField.setText("" + numberOfEntries);
                indexTextField.setText("" + (currentEntryIndex + 1));
                nextButton.setEnabled(true);
                previousButton.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertButtonActionPerformed(ActionEvent evt) {
        int result = personQueries.addPerson(
            firstNameTextField.getText(),
            lastNameTextField.getText(),
            emailTextField.getText(),
            phoneTextField.getText()
        );

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Person added!", "Person added", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Person not added!", "Error", JOptionPane.PLAIN_MESSAGE);
        }

        browseButtonActionPerformed(evt);
    }

    // método main
    public static void main(String[] args) {
        new Adress();
    }
}