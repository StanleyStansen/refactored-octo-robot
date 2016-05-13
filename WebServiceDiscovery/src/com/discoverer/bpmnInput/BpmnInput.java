package com.discoverer.bpmnInput;

public abstract class BpmnInput implements BpmnStrategy {

	public static BpmnStrategy create(BpmnStrategyType type) {
		switch (type) {
		case BpmnNamesOfAllTags:
			return new BpmnNamesOfAllTags();
		}
		return null;
	}
}
