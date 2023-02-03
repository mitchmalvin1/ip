import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    private static final String LINE = "___________________________________________";
    private static final String EXIT_STRING = "bye";
    private static final String LIST_STRING = "list";
    private static final String MARK_STRING = "mark";
    private static final String UNMARK_STRING = "unmark";

    private static boolean isExecuting;

    public static void printGreeting() {
        System.out.println("\t" + LINE);
        System.out.println("\t Hello I'm Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        System.out.println("\t" + LINE);
    }
    public static void printList() {
        System.out.println("\t" + LINE);
        System.out.println("\t Here are the tasks for your list: ");
        for(int i=0; i< counter; i++) {
            System.out.println("\t " + (i+1) + "." + taskList[i]  +  taskList[i].description);
        }
        System.out.println("\t" + LINE);
    }

    public static void printTask(Task task) {
        System.out.println("\t" + LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + counter + " tasks in your list.");
        System.out.println("\t" + LINE);
    }

    public static void printBye() {
        System.out.println("\t" + LINE);
        System.out.println("\t Bye! Do let me know if you need any further assistance");
        System.out.println("\t" + LINE);
    }
    public static void markTaskAndPrint(int taskIndex) {
        if(taskIndex < 0  || taskList[taskIndex] == null) {
            System.out.println("\t" + LINE);
            System.out.println("\t Please ensure that you enter the correct task number");
            System.out.println("\t" + LINE);
            return;
        }
        taskList[taskIndex].isDone = true;
        System.out.println("\t" + LINE);
        System.out.println("\t Nice, I have marked this task as done: ");
        System.out.println("\t [" + taskList[taskIndex].getStatusIcon() + "] " +  taskList[taskIndex].description);
        System.out.println("\t" + LINE);
    }

    public static void unmarkTaskAndPrint(int taskIndex) {
        if(taskIndex < 0 || taskList[taskIndex] == null) {
            System.out.println("\t" + LINE);
            System.out.println("\t Please ensure that you enter the correct task number");
            System.out.println("\t" + LINE);
            return;
        }
        taskList[taskIndex].isDone = false;
        System.out.println("\t" + LINE);
        System.out.println("\t Ouch, I have unmarked this task: ");
        System.out.println("\t [" + taskList[taskIndex].getStatusIcon() + "] " +  taskList[taskIndex].description);
        System.out.println("\t" + LINE);
    }

    public static void handleAddTask(String taskType, String commandInfo) {
        Task newTask = null;
        if (taskType.equals("todo")) {
            newTask = new Task(commandInfo);
        }
        else if (taskType.equals("deadline")) {
            String[] infoArr= commandInfo.split("/by");
            //infoArr contains descStr and deadlineStr respectively
            newTask = new Deadline(infoArr[0],infoArr[1]);
        }
        else if (taskType.equals("event")) {
            String[] infoArr = commandInfo.split("/from|/to");
            //infoArr contains descStr, fromStr, and toStr respectively
            newTask = new Event(infoArr[0],infoArr[1],infoArr[2]);
        }

        taskList[counter] = newTask;
        counter++;
        printTask(newTask);
    }
    public static void handleInput(String inputLine) {

        //splits input based on one or more whitespaces into two words
        String[] inputWords = inputLine.split("\\s+",2);
        String command = inputWords[0];
        //System.out.println("size: " + inputWords.length);

        if (command.equals(EXIT_STRING)) {
            printBye();
            isExecuting = false;
        }
        if (command.equals(LIST_STRING)) {
            printList();
        }
        else if (command.equals(MARK_STRING)) {
            //inputWords[1] is string that no longer contains the command string
            int indexToMark = Integer.parseInt(inputWords[1])-1; //turn it into 0-based
            markTaskAndPrint(indexToMark);
        }
        else if (command.equals(UNMARK_STRING)) {
            int indexToUnmark = Integer.parseInt(inputWords[1])-1;
            unmarkTaskAndPrint(indexToUnmark);
        }
        //check if command string matches either of the string
        else if (command.matches("todo|deadline|event")) {
            handleAddTask(command,inputWords[1]);
        }
    }


    public static void main(String[] args) {
        printGreeting();
        isExecuting = true;
        Scanner in = new Scanner(System.in);

        while(isExecuting) {
            String inputLine = in.nextLine();
            handleInput(inputLine);
        }
    }
}
