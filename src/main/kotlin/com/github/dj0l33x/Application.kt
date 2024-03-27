package com.github.dj0l33x

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@PWA(name = "Project Base for Vaadin with Spring", shortName = "Project Base")
@Theme("my-theme")
open class Application : AppShellConfigurator

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
