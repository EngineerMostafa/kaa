package org.kaaproject.kaa.server.common.core.plugin.generator.java.entity;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.kaaproject.kaa.server.common.core.plugin.generator.common.entity.Constant;

public class JavaConstant implements Constant {

    private static final String DEFAULT_TEMPLATE = "%s %s %s = %s";

    private final String name;
    private final String type;
    private final String value;
    private final Set<String> modifiers = new LinkedHashSet<>();

    public JavaConstant(String name, String type, String value, String... modifiers) {
        this.name = name;
        this.type = type;
        this.value = value;
        if (modifiers == null || modifiers.length == 0) {
            this.modifiers.addAll(Arrays.asList(new String[] { "public", "static", "final" }));
        } else {
            this.modifiers.addAll(Arrays.asList(modifiers));
        }
    }

    @Override
    public String getBody() {
        return String.format(DEFAULT_TEMPLATE, this.formatModifiers(this.modifiers), this.type, this.name, this.value).trim();
    }

    @Override
    public String toString() {
        return this.getBody();
    }

    @Override
    public Set<String> getModifiers() {
        return this.modifiers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modifiers == null) ? 0 : modifiers.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JavaConstant other = (JavaConstant) obj;
        if (modifiers == null) {
            if (other.modifiers != null) {
                return false;
            }
        } else if (!modifiers.equals(other.modifiers)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
