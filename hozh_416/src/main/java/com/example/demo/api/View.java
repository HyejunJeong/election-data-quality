package com.example.demo.api;

public class View {
    public interface StateView{}
    public interface StateData extends StateView{}
    public interface CountyView{}
    public interface CountyCoords extends CountyView{}
    public interface CountyData extends CountyView{}
    public interface PrecinctView{}
    public interface PrecinctCoords extends PrecinctView, CountyView{}
    public interface PrecinctData extends PrecinctView, CountyData{}
    public interface ErrorView{}
}
