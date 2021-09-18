import React, {FC, ReactElement, useState} from 'react';
import {ClickAwayListener, IconButton, List, ListItem} from "@material-ui/core";
import {Link} from 'react-router-dom';

import {useUserPageActionsStyles} from "./UserPageActionsStyles";
import {
    AddListsIcon,
    BlockIcon,
    EditIcon,
    LinkIcon,
    ListsIcon,
    MomentsIcon,
    MuteIcon,
    ReportIcon,
    ShareIcon,
    TopicIcon
} from "../../../icons";
import {User} from "../../../store/ducks/user/contracts/state";
import ListsModal from "../../../components/ListsModal/ListsModal";

interface UserPageActionsProps {
    user: User;
}

const UserPageActions: FC<UserPageActionsProps> = ({user}): ReactElement => {
    const classes = useUserPageActionsStyles();

    const [open, setOpen] = useState<boolean>(false);
    const [visibleListsModal, setVisibleListsModal] = useState<boolean>(false);

    const handleClick = (): void => {
        setOpen((prev) => !prev);
    };

    const handleClickAway = (): void => {
        setOpen(false);
    };

    const onOpenListsModal = (): void => {
        setVisibleListsModal(true);
    };

    const onCloseListsModal = (): void => {
        setVisibleListsModal(false);
    };

    return (
        <ClickAwayListener onClickAway={handleClickAway}>
            <div className={classes.container}>
                <IconButton
                    onClick={handleClick}
                    className={classes.messageButton}
                    color="primary"
                >
                    {EditIcon}
                </IconButton>
                {open ? (
                    <div className={classes.dropdown}>
                        <List>
                            <ListItem>
                                <span className={classes.textIcon}>{TopicIcon}</span>
                                <span className={classes.text}>View Topics</span>
                            </ListItem>
                            <ListItem onClick={onOpenListsModal}>
                                <span className={classes.textIcon}>{AddListsIcon}</span>
                                <span className={classes.text}>Add/remove @{user.username} from Lists</span>
                            </ListItem>
                            <ListItem>
                                <Link to={`/lists/memberships/${user?.id}`} className={classes.link}>
                                    <span className={classes.textIcon}>{ListsIcon}</span>
                                    <span className={classes.text}>View Lists</span>
                                </Link>
                            </ListItem>
                            <ListItem>
                                <span className={classes.textIcon}>{MomentsIcon}</span>
                                <span className={classes.text}>View Moments</span>
                            </ListItem>
                            <ListItem>
                                <span className={classes.textIcon}>{ShareIcon}</span>
                                <span className={classes.text}>Share profile via...</span>
                            </ListItem>
                            <ListItem>
                                <span className={classes.textIcon}>{LinkIcon}</span>
                                <span className={classes.text}>Copy link to profile</span>
                            </ListItem>
                            <ListItem>
                                <span className={classes.textIcon}>{MuteIcon}</span>
                                <span className={classes.text}>Mute @{user.username}</span>
                            </ListItem>
                            <ListItem>
                                <span className={classes.textIcon}>{BlockIcon}</span>
                                <span className={classes.text}>Block @{user.username}</span>
                            </ListItem>
                            <ListItem>
                                <span className={classes.textIcon}>{ReportIcon}</span>
                                <span className={classes.text}>Report @{user.username}</span>
                            </ListItem>
                        </List>
                    </div>
                ) : null}
                {visibleListsModal && <ListsModal user={user} visible={visibleListsModal} onClose={onCloseListsModal}/>}
            </div>
        </ClickAwayListener>
    );
};

export default UserPageActions;