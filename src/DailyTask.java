import java.util.Scanner;

// Task Undo Stack - Undo Mechanism
class tugasStack {
    private String[] stack;
    private int top;
    private int capacity;

    public tugasStack(int size) {
        this.capacity = size;
        this.stack = new String[size];
        this.top = -1;
    }

    public void push(String tugas) {
        // Check if the stack is full
        if (top == capacity - 1) {
            System.out.println("Stack Overflow!!.");
            return;
        }
        stack[++top] = tugas;
        System.out.println("tugas '" + tugas + "' selesai.");
    }

    public String pop() {
        // Check if the stack is empty
        if (top == -1) {
            System.out.println("Stack Kosong!.");
            return null;
        }
        return stack[top--];
    }

    public String peek() {
        // Check if the stack is empty
        if (top == -1) {
            return "Tidak ada tugas yanng selesai";
        }
        return stack[top];
}}

// Dyamic Task with Linked List - Node 
class TaskNode {
    String task;
    TaskNode next;

    // Constructor
    public TaskNode(String tugas) {
        this.task = tugas;
        this.next = null;
}}

// Dynamic Task with Linked List
class TaskLinkedList {
    private TaskNode TugasPertama;
    // Insert Task
    public void insertTask(String tugas) {
        TaskNode newNode = new TaskNode(tugas);
        if (TugasPertama == null) {
            TugasPertama = newNode;
        } else {
            TaskNode current = TugasPertama;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Task '" + tugas + "' ditambahkan ke linked list.");
    }   

    // Remove Task
    public void deleteTask(int posisi) {
        if (TugasPertama == null) {
            System.out.println("tidak ada tugas yang mau dihapus");
            return;
        }
        if (posisi == 0) {
            System.out.println("tugas '" + TugasPertama + "'dihapus dari linked.");
            TugasPertama = TugasPertama.next;
            return;
        }

        TaskNode current = TugasPertama;
        TaskNode previous = null;
        int count = 0;

        while (current != null && count < posisi) {
            previous = current;
            current = current.next;
            count++;
        }

        if (current != null) {
            System.out.println("tugas '" + TugasPertama + "'Dihapus dari linked list.");
            previous.next = current.next;
        } else {
            System.out.println("tidak valid, tidak ada tugas yang dihapus.");
        }
    }

    // Display Task
    public void displayTasks() {
        if (TugasPertama == null) {
            System.out.println("Tidak ada tugas tersimpan dalam linked list.");
            return;
        }

        System.out.println("Tugas yang tersimpan di linked list:");
        TaskNode current = TugasPertama;
        while (current != null) {
            System.out.println("- " + current.task);
            current = current.next;
        }
    }
}

// Main
public class DailyTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String RED = "\u001b[38;5;214m"; // Red bold text
        String RESET = "\u001b[0m"; // Reset color
        // Task Array
        String[] taskArray = {"Cek Email", "Latihan", "Main Piano", "Memasak", "Mencuci" };
        // Task Undo Stack
        tugasStack completedTasks = new tugasStack(10);
        // Dynamic Task with Linked List
        TaskLinkedList taskList = new TaskLinkedList();

        while (true) {
            System.out.println("=================");
            System.out.println("~Tugas Harian ~");
            System.out.println("==================");
            System.out.println("1. Lihat Tugas");
            System.out.println("2. Update Tugas");
            System.out.println("3. Membatalkan Tugas");
            System.out.println("4. Undo Last Completion");
            System.out.println("5. TambahTugas baru");
            System.out.println("6. Hapus Tugas");
            System.out.println("7. Lihat Tugas baru");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
            case 1:
                // Task Array - Display predefined tasks
                System.out.println("\n tugas di Array:");
                // Display the predefined tasks
                for (int i = 0; i < taskArray.length; i++) {
                    System.out.println((i + 1) + ". " + taskArray[i]);
                }
            break;

            case 2:
                // Task Array - Update task
                System.out.print("masukkan tugas yang mau diupdate: ");
                int Number = scanner.nextInt(); 
                scanner.nextLine(); // Consume newline
                // Check if the index is valid and updates the task
                if (Number >= 0 && Number < taskArray.length) {
                    System.out.print("masukkan tugas baru: ");
                    taskArray[Number] = scanner.nextLine(); // Update the task in the array using user input scanner.nextLine();
                    System.out.println("Tugas berhasil di update.");
                } else {
                    System.out.println(RED + "Number tidak valid." + RESET);
                }
            break;

            case 3:
                // Task Stack - Mark task as completed - Push to Stack
                System.out.print("Tugas yang selesai  ");
                int IndexTugas = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                // Check if the index is valid and then verifies it
                if (IndexTugas >= 0 && IndexTugas < taskArray.length) { // Check if the index is valid
                    String TugasYangSelesai = taskArray[IndexTugas];
                    completedTasks.push(TugasYangSelesai);
                    System.out.println("Tugas '" + TugasYangSelesai + "'selesai.");
                } else {
                    System.out.println(RED + "Number tidak valid."+ RESET);
                }
            break;

            case 4:
                // Task Stack - Undo last completion - Pop from Stack
                String undoneTask = completedTasks.pop();
                // Check if there is a task to undo
                if (undoneTask != null) {
                    System.out.println("Task '" + undoneTask + "' undone.");
                }
            break;

            case 5:
                // Dynamic Task with Linked List - Add new task
                System.out.print("masukkan tugas yang mau ditambahkan: ");
                String TugasBaru = scanner.nextLine();
                // Add dynamic task into linked list
                taskList.insertTask(TugasBaru);
            break;

            case 6:
                // Dynamic Task with Linked List - Remove task
                System.out.print("Tugas Nomor Berapa Yang Mau Dihapus: ");
                int HapusNomorPosisi = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                // Remove dynamic task from linked list
                taskList.deleteTask(HapusNomorPosisi);
            break;

            case 7:
                // View dynamic task(s) in linked list
                taskList.displayTasks();            
            break;

            case 8: 
                // Exit the program
                System.out.println("terimakasih.");
                System.exit(0);
                scanner.close();
            break;

                default:
                System.out.println(RED + "Coba lagi." + RESET);
        }
    }
}}