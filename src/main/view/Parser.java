//package view;
//
//import model.Storage;
//
//public class Parser {
//
//    private Storage storage;
//    public static TaskList tasks = new TaskList();
//
//    public static String getCommandWord(String fullCommand) {
//        String[] s = fullCommand.split(" ");
//        String command = s[0];
//        return command;
//    }
//
//    public static void createTodo(String fullCommand) throws TaskManagerException {
//        Todo todo = null;
//        String description = fullCommand.substring("todo".length()).trim();
//        if (description.isEmpty()) {
//            throw new TaskManagerException("Empty description for TODO");
//        } else {
//            todo = new Todo(description);
//        }
//        tasks.addTask(todo);
//    }
//
//    public static void createDeadline(String fullCommand) throws TaskManagerException {
//        Deadline deadline = null;
//        int idxOfBy = fullCommand.indexOf("/by");
//        String description = fullCommand.substring("deadline".length(), idxOfBy).trim();
//        if (description.isEmpty()) {
//            throw new TaskManagerException("Empty description for DEADLINE");
//        } else {
//            String deadLine = fullCommand.substring(idxOfBy, fullCommand.length()).substring("/by".length()).trim();
//            deadline = (new Deadline(description, deadLine));
//        }
//        tasks.addTask(deadline);
//    }
//}
