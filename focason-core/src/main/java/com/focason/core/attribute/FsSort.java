// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.attribute;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Objects;

public record FsSort(String property,Direction direction){

public String toSql(){return this.property+" "+this.direction.getValue();}

public static FocasonSortBuilder builder(){return new FocasonSortBuilder();}

public boolean equals(final Object o){if(o==this){return true;}else if(!(o instanceof FsSort other)){return false;}else{Object this$property=this.property();Object other$property=other.property();if(this$property==null){if(other$property!=null){return false;}}else if(!this$property.equals(other$property)){return false;}

Object this$direction=this.direction();Object other$direction=other.direction();if(this$direction==null){return other$direction==null;}else return this$direction.equals(other$direction);}}

public int hashCode(){Object $property=this.property();int result=59+($property==null?43:$property.hashCode());Object $direction=this.direction();result=result*59+($direction==null?43:$direction.hashCode());return result;}

public String toString(){String var10000=this.property();return"FocasonSort(property="+var10000+", direction="+this.direction()+")";}

public FsSort withProperty(final String property){return Objects.equals(this.property,property)?this:new FsSort(property,this.direction);}

public FsSort withDirection(final Direction direction){return this.direction==direction?this:new FsSort(this.property,direction);}

public static class FocasonSortBuilder
{
    private String property;
    private Direction direction;

    FocasonSortBuilder() {}

    public FocasonSortBuilder property(final String property) {
        this.property = property;
        return this;
    }

    public FocasonSortBuilder direction(final Direction direction) {
        this.direction = direction;
        return this;
    }

    public FsSort build() {
        return new FsSort(this.property, this.direction);
    }

    public String toString() {
        return "FocasonSort.FocasonSortBuilder(property=" + this.property + ", direction=" + this.direction + ")";
    }
}


public static enum Direction
{
    ASC("ASC"), DESC("DESC");

    private final String value;

    @JsonCreator
    public static Direction of(String value) {
        return Arrays.stream(values())
            .filter((v) -> v.value.equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Direction = '" + value + "' is not supported."));
    }

    Direction(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}}
