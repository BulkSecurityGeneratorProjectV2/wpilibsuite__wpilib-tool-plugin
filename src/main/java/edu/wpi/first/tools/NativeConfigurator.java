package edu.wpi.first.tools;

import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.dsl.DependencyHandler;


public class NativeConfigurator {
    private final PlatformMapper platformMapper;
    private String defaultJavaFxVersion = "17";
    private String wpilibVersion = "+";
    private final DependencyHandler handler;

    public NativeConfigurator(PlatformMapper mapper, DependencyHandler handler) {
        this.platformMapper = mapper;
        this.handler = handler;
    }

    public Dependency wpilibJava(String name) {
        return wpilibJava(name, wpilibVersion);
    }

    public Dependency wpilibJava(String name, String version) {
        return handler.create("edu.wpi.first." + name + ":" + name + "-java:" + version);
    }

    public Dependency wpilib(String name, String version) {
        return handler.create("edu.wpi.first." + name + ":" + name + "-cpp:" + version + ":" + platformMapper.getWpilibClassifier() + "@zip");
    }

    public Dependency wpilib(String name) {
        return wpilib(name, this.wpilibVersion);
    }

    public Dependency cscore(String version) {
        return handler.create("edu.wpi.first.cscore:cscore-jnicvstatic:" + version + ":" + platformMapper.getWpilibClassifier()  + "@zip");
    }

    public Dependency cscore() {
        return cscore(this.wpilibVersion);
    }

    public Dependency javafx(String name, String version) {
        String groupName = "org.openjfx";
        return handler.create(groupName + ":javafx-" + name + ":" + version + ":" + platformMapper.getJavaFxClassifier());
    }

    public Dependency javafx(String name) {
        return javafx(name, defaultJavaFxVersion);
    }

    public void setDefaultJavaFxVersion(String version) {
        defaultJavaFxVersion = version;
    }

    public void setWpilibVersion(String version) {
        this.wpilibVersion = version;
    }

    // public static Dependency nativeDependency(DependencyHandler handler, String group, String name, String version, Function<NativePlatforms, String> classiferFunction) {
    //     NativePlatforms currentPlatform = PlatformMapper.getCurrentPlatform();
    //     return handler.add(currentPlatform.getPlatformName(),
    //         group + ":" + name + ":" + version + ":" + classiferFunction.apply(currentPlatform));
    // }
}
