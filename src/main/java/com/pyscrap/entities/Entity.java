package com.pyscrap.entities;

import org.joml.Vector3f;

import com.pyscrap.models.TexturedModel;

public class Entity {
    private TexturedModel model;
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;
    private Vector3f size;

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale, Vector3f size) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.size = size;
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public Vector3f getSize() {
        return size;
    }
}
