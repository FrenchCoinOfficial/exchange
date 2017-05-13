package io.bisq.network.p2p.storage.messages;

import io.bisq.common.network.NetworkEnvelope;
import io.bisq.common.proto.NetworkProtoResolver;
import io.bisq.generated.protobuffer.PB;
import io.bisq.network.p2p.storage.payload.ProtectedMailboxStorageEntry;
import lombok.Value;

@Value
public final class RemoveMailboxDataMessage extends BroadcastMessage {
    private final ProtectedMailboxStorageEntry protectedMailboxStorageEntry;

    public RemoveMailboxDataMessage(ProtectedMailboxStorageEntry protectedMailboxStorageEntry) {
        this.protectedMailboxStorageEntry = protectedMailboxStorageEntry;
    }

    @Override
    public PB.NetworkEnvelope toProtoNetworkEnvelope() {
        return NetworkEnvelope.getDefaultBuilder()
                .setRemoveMailboxDataMessage(PB.RemoveMailboxDataMessage.newBuilder()
                        .setProtectedStorageEntry(protectedMailboxStorageEntry.toProtoMessage()))
                .build();
    }

    public static RemoveMailboxDataMessage fromProto(PB.RemoveMailboxDataMessage proto, NetworkProtoResolver resolver) {
        return new RemoveMailboxDataMessage(ProtectedMailboxStorageEntry.fromProto(proto.getProtectedStorageEntry(), resolver));
    }
}