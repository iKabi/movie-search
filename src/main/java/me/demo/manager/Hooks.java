package me.demo.manager;

public interface Hooks {

	default void init(){}

	default void shutDown(){}

}
