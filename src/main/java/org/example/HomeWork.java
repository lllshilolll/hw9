package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу UPIT из файла contest7_tasks.pdf
     */
    @SneakyThrows
    public void upit(InputStream in, OutputStream out) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        List<Long> list = new ArrayList<>();
        List<List<Integer>> operations = new ArrayList<>();
        long size = 0;
        long count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (size == 0 && count == 0) {
                String[] arr = line.split(" ");
                size = Long.parseLong(arr[0]);
                count = Long.parseLong(arr[1]);
            } else if (list.isEmpty()) {
                list.addAll(Arrays.stream(line.split(" ")).map(Long::parseLong).collect(Collectors.toList()));
            } else {
                var operation = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                operations.add(operation);
            }
        }
        bufferedReader.close();

        operations.forEach(op ->
                {
                    switch (op.get(0)) {
                        case 1: {
                            var start = op.get(1) - 1;
                            var finish = op.get(2) - 1;
                            var value = op.get(3);
                            for (int i = start; i <= finish; i++) {
                                list.set(i, Long.valueOf(value));
                            }
                            break;
                        }
                        case 2: {
                            var start = op.get(1) - 1;
                            var finish = op.get(2) - 1;
                            var value = op.get(3);
                            var count2 = 1;
                            for (int i = start; i <= finish; i++) {
                                list.set(i, (long) value * count2 + list.get(i));
                                count2++;
                            }
                            break;
                        }
                        case 3: {
                            list.add(op.get(1) - 1, Long.valueOf(op.get(2)));
                            break;
                        }
                        case 4: {
                            var start = op.get(1) - 1;
                            var finish = op.get(2) - 1;
                            var sum = 0L;
                            for (int i = start; i <= finish; i++) {
                                sum = sum + list.get(i);
                            }
                            stringBuilder.append(sum).append("\n");
                            break;
                        }
                        default:
                            throw new RuntimeException("Неизвестный тип");
                    }
                }
        );
        out.write(stringBuilder.toString().getBytes());
        out.close();
    }
}
