package licos.routine.protocol.async.village

import licos.entity.VillageInfoFromLobby
import licos.protocol.engine.processing.village.VillageBOX

final class VillageBox(override val villageInfoFromLobby: VillageInfoFromLobby) extends VillageBOX(villageInfoFromLobby)
