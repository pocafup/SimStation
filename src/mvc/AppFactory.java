package mvc;

public interface AppFactory {
    Model makeModel();

    View makeView(Model model);

    String[] getEditCommands();

    Command makeEditCommand(Model model, String commandName, Object source);

    String getHelp();

    String about();

    String getTitle();
}