package learn.sprsec.ssia0502context.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        log.trace("Hello, {}!", username);
        return "Hello, " + username + "!";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        log.trace("Goodbye, {}!", username);
    }

    @GetMapping("/ciao")
    public String ciao() throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            String username = context.getAuthentication().getName();
            log.trace("Ciao, {}!", username);
            return username;
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + executor.submit(contextTask).get() + "!";
        } finally {
            executor.shutdown();
        }
    }

    @GetMapping("/hola")
    public String hola() throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            String username = context.getAuthentication().getName();
            log.trace("Hola, {}!", username);
            return username;
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor = new DelegatingSecurityContextExecutorService(executor);
        try {
            return "Hola, " + executor.submit(task).get() + "!";
        } finally {
            executor.shutdown();
        }
    }
}
