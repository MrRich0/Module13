package org.example;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        User user = new User();
        HttpUtil util = new HttpUtil();
        Scanner scanner = new Scanner(System.in);

        user.setId(1);
        user.setName("Vladimir");
        user.setUsername("Motyka");
        user.setEmail("@gmail.com");

        System.out.println("###TASK 1.1###");
        System.out.println(util.sendPost(user));
        System.out.println();

        System.out.println("###TASK 1.2###");
        user.setId(14);
        user.setName("Igor");
        user.setUsername("Primo");
        user.setEmail("1@gmail.com");

        System.out.print("Введіть номер ID де потрібно замінити інформацію:");
        String idPut = scanner.nextLine();
        System.out.println(util.sendPut(idPut,user));
        System.out.println();

        System.out.println("###TASK 1.3###");
        System.out.print("Введіть номер ID інформацію якого потрібно видалити:");
        String idDelete = scanner.nextLine();
        util.sendDelete(idDelete);
        System.out.println();

        System.out.println("###TASK 1.4###");
        util.sendGetAll();
        System.out.println();

        System.out.println("###TASK 1.5###");
        System.out.print("Введіть номер ID інформацію якого потрібно надрукувати:");
        String idGetById = scanner.nextLine();
        util.sendGetById(idGetById);
        System.out.println();

        System.out.println("###TASK 1.6###");
        System.out.print("Введіть UserName інформацію якого потрібно надрукувати:");
        String getByUserName = scanner.nextLine();
        util.sendGetByUserName(getByUserName);
        System.out.println();

        System.out.println("###TASK 2###");
        System.out.print("Введіть номер ID user,щоб вивести всі коментарі до останнього поста:");
        String idFindByIdPostMax = scanner.nextLine();
        util.findByIdPostMaxComments(idFindByIdPostMax);
        System.out.println();

        System.out.println("###TASK 3###");
        System.out.print("Введіть номер ID user,щоб вивести всі його відкриті задачі:");
        String idSendTodo = scanner.nextLine();
        util.sendTodo(idSendTodo);
        System.out.println();


    }

}