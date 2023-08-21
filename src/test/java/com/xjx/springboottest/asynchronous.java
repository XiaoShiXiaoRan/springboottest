package com.xjx.springboottest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class asynchronous {

    /**
     * // 带返回值异步请求，默认线程池
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     *
     * // 带返回值的异步请求，可以自定义线程池
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     *
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//            System.out.println("do something....");
//            return "result";
//        });
//
//        //等待任务执行完成
//        System.out.println("结果->" + cf.get());

        /**
         * ========================
         */
        // 自定义线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something....");
            return "result";
        }, executorService);

        //等待子任务执行完成
        System.out.println("结果->" + cf.get());
    }


}
