package com.anabneri.netlfix.model;

import java.util.Objects;

public class NetflixEvent {
    private Long netflixId;
    private String netflixEventType;

    public NetflixEvent(final Long netflixId, final String netflixEventType) {
        this.netflixId = netflixId;
        this.netflixEventType = netflixEventType;
    }


    public void setNetflixEventType(final String netflixEventType) {
        this.netflixEventType = netflixEventType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NetflixEvent that = (NetflixEvent) o;
        return Objects.equals(netflixId, that.netflixId) && Objects.equals(netflixEventType, that.netflixEventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netflixId, netflixEventType);
    }

    @Override
    public String toString() {
        return "NetflixEvent{" +
            "netflixId=" + netflixId +
            ", netflixEventType='" + netflixEventType + '\'' +
            '}';
    }

}
