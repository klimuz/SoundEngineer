package uz.klimuz.soundengineer;

import java.util.ArrayList;

public class RioDistributor {
    public ArrayList<String> distributeStageBoxes(int channelNumber) {
        ArrayList<String> nameNumber = new ArrayList<>();
        String rioName = "";
        String rioNumber = "";
        int numberOfStageBoxChannels = 0;
        switch (ChooseStageBoxActivity.spinner1Position) {
            case 1: {
                numberOfStageBoxChannels = 32;
                if (channelNumber <= numberOfStageBoxChannels) {
                    rioName = "RIO3224";
                    rioNumber = String.format("%s %s", "1/", channelNumber);
                    return prepareList(rioName, rioNumber);
                }
                break;
            }
            case 2: {
                numberOfStageBoxChannels = 64;
                if (channelNumber <= numberOfStageBoxChannels) {
                    rioName = "RPIO622";
                    if (channelNumber <= 16) {
                        rioNumber = String.format("%s %s", "1/", channelNumber);
                    } else if (channelNumber <= 32) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - 16);
                    } else if (channelNumber <= 48) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - 32);
                    } else {
                        rioNumber = String.format("%s %s", "4/", channelNumber - 48);
                    }
                    return prepareList(rioName, rioNumber);
                }
                break;
            }
            case 3: {
                numberOfStageBoxChannels = 16;
                rioName = "SD16";
                if (channelNumber <= numberOfStageBoxChannels) {
                    rioNumber = String.format("%s %s", "1/", channelNumber);
                    return prepareList(rioName, rioNumber);
                }
                break;
            }
        }
        switch (ChooseStageBoxActivity.spinner2Position) {
            case 1: {
                if (numberOfStageBoxChannels + 32 >= channelNumber && channelNumber > numberOfStageBoxChannels) {
                    rioName = "RIO3224";
                    if (ChooseStageBoxActivity.spinner1Position == 1) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    } else {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 32;
                break;
            }
            case 2: {
                if (channelNumber > numberOfStageBoxChannels && channelNumber <= numberOfStageBoxChannels + 64) {
                    rioName = "RPIO622";
                    if (channelNumber - numberOfStageBoxChannels <= 16) {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    } else if (channelNumber - numberOfStageBoxChannels <= 32) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels - 16);
                    } else if (channelNumber - numberOfStageBoxChannels <= 48) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels - 32);
                    } else {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels - 48);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 64;
                break;
            }
            case 3: {
                if (channelNumber <= 16 + numberOfStageBoxChannels && channelNumber > numberOfStageBoxChannels) {
                    rioName = "SD16";
                    if (ChooseStageBoxActivity.spinner1Position == 3) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    } else {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 16;
                break;
            }
        }
        switch (ChooseStageBoxActivity.spinner3Position) {
            case 1: {
                if (numberOfStageBoxChannels + 32 >= channelNumber && channelNumber > numberOfStageBoxChannels) {
                    rioName = "RIO3224";
                    if (ChooseStageBoxActivity.spinner1Position == 1 && ChooseStageBoxActivity.spinner2Position == 1) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels);
                    } else if (ChooseStageBoxActivity.spinner1Position != 1 && ChooseStageBoxActivity.spinner2Position == 1) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 32;
                break;
            }
            case 2: {
                if (channelNumber > numberOfStageBoxChannels && channelNumber <= numberOfStageBoxChannels + 64) {
                    rioName = "RPIO622";
                    if (channelNumber - numberOfStageBoxChannels <= 16) {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    } else if (channelNumber - numberOfStageBoxChannels <= 32) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels - 16);
                    } else if (channelNumber - numberOfStageBoxChannels <= 48) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels - 32);
                    } else {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels - 48);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 64;
                break;
            }
            case 3: {
                if (channelNumber <= 16 + numberOfStageBoxChannels && channelNumber > numberOfStageBoxChannels) {
                    rioName = "SD16";
                    if (ChooseStageBoxActivity.spinner1Position == 3) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    } else {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 16;
                break;
            }
        }
        switch (ChooseStageBoxActivity.spinner4Position) {
            case 1: {
                if (numberOfStageBoxChannels + 32 >= channelNumber && channelNumber > numberOfStageBoxChannels) {
                    rioName = "RIO3224";
                    if (ChooseStageBoxActivity.spinner1Position == 1 && ChooseStageBoxActivity.spinner2Position == 1) {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels);
                    } else if (ChooseStageBoxActivity.spinner1Position != 1 && ChooseStageBoxActivity.spinner2Position == 1) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 32;
                break;
            }
            case 2: {
                if (channelNumber > numberOfStageBoxChannels && channelNumber <= numberOfStageBoxChannels + 64) {
                    rioName = "RPIO622";
                    if (channelNumber - numberOfStageBoxChannels <= 16) {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    } else if (channelNumber - numberOfStageBoxChannels <= 32) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels - 16);
                    } else if (channelNumber - numberOfStageBoxChannels <= 48) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels - 32);
                    } else {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels - 48);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 64;
                break;
            }
            case 3: {
                if (channelNumber <= 16 + numberOfStageBoxChannels && channelNumber > numberOfStageBoxChannels) {
                    rioName = "SD16";
                    if (ChooseStageBoxActivity.spinner1Position == 3) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    } else {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 16;
                break;
            }
        }
        switch (ChooseStageBoxActivity.spinner5Position) {
            case 1: {
                if (numberOfStageBoxChannels + 32 >= channelNumber && channelNumber > numberOfStageBoxChannels) {
                    rioName = "RIO3224";
                    if (ChooseStageBoxActivity.spinner1Position != 1 && ChooseStageBoxActivity.spinner2Position == 1) {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 32;
                break;
            }
            case 2: {
                if (channelNumber > numberOfStageBoxChannels && channelNumber <= numberOfStageBoxChannels + 64) {
                    rioName = "RPIO622";
                    if (channelNumber - numberOfStageBoxChannels <= 16) {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    } else if (channelNumber - numberOfStageBoxChannels <= 32) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels - 16);
                    } else if (channelNumber - numberOfStageBoxChannels <= 48) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels - 32);
                    } else {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels - 48);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 64;
                break;
            }
            case 3: {
                if (channelNumber <= 16 + numberOfStageBoxChannels && channelNumber > numberOfStageBoxChannels) {
                    rioName = "SD16";
                    if (ChooseStageBoxActivity.spinner4Position == 3) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    } else {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                numberOfStageBoxChannels += 16;
                break;
            }
        }
        switch (ChooseStageBoxActivity.spinner6Position) {
            case 1: {
                if (numberOfStageBoxChannels + 32 >= channelNumber && channelNumber > numberOfStageBoxChannels) {
                    rioName = "RIO3224";
                    if (ChooseStageBoxActivity.spinner1Position == 1 && ChooseStageBoxActivity.spinner2Position == 1
                    && ChooseStageBoxActivity.spinner3Position == 1 && ChooseStageBoxActivity.spinner4Position == 1
                    && ChooseStageBoxActivity.spinner5Position == 1) {
                        rioNumber = String.format("%s %s", "6/", channelNumber - numberOfStageBoxChannels);
                    }else if (ChooseStageBoxActivity.spinner1Position != 1 && ChooseStageBoxActivity.spinner2Position == 1
                            && ChooseStageBoxActivity.spinner3Position == 1 && ChooseStageBoxActivity.spinner4Position == 1
                            && ChooseStageBoxActivity.spinner5Position == 1){
                        rioNumber = String.format("%s %s", "5/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                break;
            }
            case 2: {
                if (channelNumber > numberOfStageBoxChannels && channelNumber <= numberOfStageBoxChannels + 64) {
                    rioName = "RPIO622";
                    if (channelNumber - numberOfStageBoxChannels <= 16) {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    } else if (channelNumber - numberOfStageBoxChannels <= 32) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels - 16);
                    } else if (channelNumber - numberOfStageBoxChannels <= 48) {
                        rioNumber = String.format("%s %s", "3/", channelNumber - numberOfStageBoxChannels - 32);
                    } else {
                        rioNumber = String.format("%s %s", "4/", channelNumber - numberOfStageBoxChannels - 48);
                    }
                    return prepareList(rioName, rioNumber);
                }
                break;
            }
            case 3: {
                if (channelNumber <= 16 + numberOfStageBoxChannels && channelNumber > numberOfStageBoxChannels) {
                    rioName = "SD16";
                    if (ChooseStageBoxActivity.spinner5Position == 3) {
                        rioNumber = String.format("%s %s", "2/", channelNumber - numberOfStageBoxChannels);
                    } else {
                        rioNumber = String.format("%s %s", "1/", channelNumber - numberOfStageBoxChannels);
                    }
                    return prepareList(rioName, rioNumber);
                }
                break;
            }
            default: {
                rioName = "ANALOG";
                rioNumber = String.format("%s %s", "A/", channelNumber);
                break;
            }
        }
        return prepareList(rioName, rioNumber);
    }

    private ArrayList<String> prepareList(String name, String number) {
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        list.add(number);
        return list;
    }
}
