package me.nalab.api.sentry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/sentry")
public class SentryController {

    @GetMapping("/info")
    public String info() {
        log.info("[info]sentry check");
        return "info";
    }

    @GetMapping("/warn")
    public String warn() {
        log.warn("[warn]sentry check");
        return "warn";
    }

    @GetMapping("/error")
    public String error() {
        log.error("[error]sentry check");
        return "error";
    }

}
