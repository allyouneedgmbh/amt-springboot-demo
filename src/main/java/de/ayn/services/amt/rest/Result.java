package de.ayn.services.amt.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Result<D> {

    public static enum State {
        OK, ERROR
    }

    private State state = State.OK;

    private D data;

    private List<String> messages = new ArrayList<>();

    public Result() {
        super();
    }

    public Result(State state, D data, Collection<String> messages) {
        super();
        this.state = state;
        this.data = data;
        this.messages.addAll(messages);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Result)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        Result castOther = (Result) other;
        return Objects.equals(state, castOther.state) && Objects.equals(data, castOther.data) && Objects.equals(messages, castOther.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, data, messages);
    }

    @Override
    public String toString() {
        return String.format("Result [state=%s, data=%s, messages=%s]", state, data, messages);
    }

}
